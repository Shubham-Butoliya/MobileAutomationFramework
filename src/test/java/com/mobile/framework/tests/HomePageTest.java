package com.mobile.framework.tests;

import com.mobile.framework.utils.ExtentManager;
import com.mobile.framework.listeners.RetryAnalyzer;
import com.mobile.framework.listeners.TestListener;
import com.mobile.framework.pages.HomePage;
import com.mobile.framework.pages.MenuScreen;
import org.testng.Assert;
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
        Assert.assertTrue(homePage.isHomePage());
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testLogoutFromHomePageSuccess() {
        ExtentManager.getTest().info("Testing logout for user");
        MenuScreen menu = homePage.goToMenu();
        loginPage = menu.logout();
        Assert.assertTrue(loginPage.isOnLoginPage());
    }

}
