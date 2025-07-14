package com.mobile.framework.pages;

import com.mobile.framework.actions.MobileActions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MenuScreen {

    private AppiumDriver driver;
    private MobileActions actions;

    public MenuScreen(AppiumDriver driver) {
        this.driver = driver;
        this.actions = new MobileActions(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"LOGOUT\")")
    @iOSXCUITFindBy(accessibility = "test-LOGOUT")
    private WebElement logoutButton;

    public LoginPage logout() {
        actions.tap(logoutButton);
        return new LoginPage(driver);
    }

}
