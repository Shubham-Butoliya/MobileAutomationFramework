package com.mobile.framework.tests;

import com.mobile.framework.drivermanager.DriverFactory;
import com.mobile.framework.pages.LoginPage;
import com.mobile.framework.utils.JsonUtils;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected AppiumDriver driver;
    protected LoginPage loginPage;

    @Parameters("platform")
    @BeforeClass
    public void setup(String platform, ITestContext context) throws Exception {
        context.setAttribute("platform", platform);
        driver = DriverFactory.getDriver(platform);
        loginPage = new LoginPage(driver);
    }

    @DataProvider(name = "loginDataProvider")
    public Object[][] loginData() {
        return JsonUtils.readLoginData("src/test/resources/testdata/loginData.json");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }

}
