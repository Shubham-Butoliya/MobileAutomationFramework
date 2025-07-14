package com.mobile.framework.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.mobile.framework.drivermanager.DriverFactory;
import com.mobile.framework.utils.ExtentManager;
import com.mobile.framework.utils.ScreenshotUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    public void onTestStart(ITestResult result) {
//        ExtentManager.createTest(result.getMethod().getMethodName());
        ITestContext context = result.getTestContext();
        String platform = (String) context.getAttribute("platform");

        if (platform == null) {
            platform = "UnknownPlatform";
        }

        ExtentTest test = ExtentManager.createTest(result.getMethod().getMethodName() + " [" + platform + "]");
        test.assignCategory(platform);
    }

    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().pass("Passed");
    }

    public void onTestFailure(ITestResult result) {
        ExtentTest test = ExtentManager.getTest();
        test.fail(result.getThrowable());

        String screenshotPath = ScreenshotUtils.captureScreenshot(DriverFactory.getCurrentDriver(), result.getMethod().getMethodName());
        try {
            test.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            test.fail("Test Failed, screenshot unavailable.");
        }
    }

    public void onTestSkipped(ITestResult result) {
        ExtentManager.getTest().skip("Skipped");
    }

    public void onFinish(ITestContext context) {
        ExtentManager.flush();
    }
}
