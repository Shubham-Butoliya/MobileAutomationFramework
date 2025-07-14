package com.mobile.framework.pages;

import com.mobile.framework.actions.MobileActions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private AppiumDriver driver;
    private MobileActions actions;

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
        this.actions = new MobileActions(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(accessibility = "test-Username")
    @iOSXCUITFindBy(accessibility = "test-Username")
    private WebElement usernameText;

    @AndroidFindBy(accessibility = "test-Password")
    @iOSXCUITFindBy(accessibility = "test-Password")
    private WebElement passwordText;

    @AndroidFindBy(accessibility = "test-LOGIN")
    @iOSXCUITFindBy(accessibility = "test-LOGIN")
    private WebElement loginButton;

    public void enterUsername(String username) {
        actions.enterText(usernameText, username);
    }

    public void enterPassword(String password) {
        actions.enterText(passwordText, password);
    }

    public HomePage clickLoginButton() {
        actions.tap(loginButton);
        return new HomePage(driver);
    }

    public boolean isOnLoginPage() {
        return actions.isElementDisplayed(usernameText);
    }

}
