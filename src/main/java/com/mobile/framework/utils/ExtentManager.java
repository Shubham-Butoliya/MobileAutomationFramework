package com.mobile.framework.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static final ExtentReports extent = new ExtentReports();
    private static final ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    static {
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
        extent.attachReporter(spark);
    }

    public static ExtentTest createTest(String name) {
        ExtentTest test = extent.createTest(name);
        testThread.set(test);
        return test;
    }

    public static ExtentTest getTest() {
        return testThread.get();
    }

    public static void flush() {
        extent.flush();
    }

}
