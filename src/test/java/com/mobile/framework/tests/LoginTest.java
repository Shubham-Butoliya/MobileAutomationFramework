package com.mobile.framework.tests;

import com.mobile.framework.pages.HomePage;
import com.mobile.framework.testUtils.AssertUtils;
import com.mobile.framework.utils.ExtentManager;
import com.mobile.framework.listeners.RetryAnalyzer;
import com.mobile.framework.listeners.TestListener;
import org.testng.annotations.*;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginDataProvider")
    public void testLogin(String user, String pass) {
        ExtentManager.getTest().info("Testing login for user: " + user);
        loginPage.enterUsername(user);
        loginPage.enterPassword(pass);
        loginPage.clickLoginButton();
        HomePage homePage = loginPage.clickLoginButton();
        AssertUtils.assertTrue(homePage.isHomePage(), "Home page is visible");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testLogout() {
        ExtentManager.getTest().info("Testing logout for user");
        AssertUtils.fail("intentionally fail");
    }

}
