package com.mobile.framework.pages;

import com.mobile.framework.actions.MobileActions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.Collections;

public class HomePage {

    private AppiumDriver driver;
    private MobileActions actions;

    public HomePage(AppiumDriver driver) {
        this.driver = driver;
        this.actions = new MobileActions(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").instance(1)")
    @iOSXCUITFindBy(accessibility = "test-Menu")
    private WebElement menuButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.ViewGroup\").instance(14)")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"test-Menu\"]/XCUIElementTypeOther")
    private WebElement productHeader;

    public boolean isHomePage() {
        return actions.isElementDisplayed(productHeader);
    }

    public MenuScreen goToMenu() {
        actions.tap(menuButton);
        actions.tapUsingActions(menuButton);

        Point point = menuButton.getLocation();
        Dimension size = menuButton.getSize();
        int centerX = point.getX() + size.getWidth() / 2;
        int centerY = point.getY() + size.getHeight() / 2;

// Use W3C Actions
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(tap));

        return new MenuScreen(driver);
    }
}
