package com.mobile.framework.tests;

import com.mobile.framework.testUtils.AssertUtils;
import com.mobile.framework.utils.ExtentManager;
import com.mobile.framework.listeners.RetryAnalyzer;
import com.mobile.framework.listeners.TestListener;
import com.mobile.framework.pages.HomePage;
import com.mobile.framework.pages.MenuScreen;
import org.testng.annotations.*;

@Listeners(TestListener.class)
public class HomePageTest extends BaseTest {

    private HomePage homePage;

    @Test(dataProvider = "loginDataProvider")
    public void testHomePage(String user, String pass) {
        ExtentManager.getTest().info("Testing login for user: " + user);
        loginPage.enterUsername(user);
        loginPage.enterPassword(pass);
        homePage = loginPage.clickLoginButton();
        AssertUtils.assertTrue(homePage.isHomePage(), "Home page is visible");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testLogoutFromHomePageSuccess() {
        ExtentManager.getTest().info("Testing logout for user");
        MenuScreen menu = homePage.goToMenu();
        loginPage = menu.logout();
        AssertUtils.assertTrue(loginPage.isOnLoginPage(), "Login page is visible");
    }

}
