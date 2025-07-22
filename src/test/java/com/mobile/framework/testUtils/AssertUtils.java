package com.mobile.framework.testUtils;

import com.mobile.framework.utils.ExtentManager;
import org.testng.Assert;

public class AssertUtils {

    public static void assertEquals(Object actual, Object expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
            ExtentManager.getTest().pass("Assertion Passed: " + message + " | Expected: " + expected + ", Actual: " + actual);
        } catch (AssertionError e) {
            ExtentManager.getTest().fail("Assertion Failed: " + message + " | Expected: " + expected + ", Actual: " + actual);
            throw e;
        }
    }

    public static void assertTrue(boolean condition, String message) {
        try {
            Assert.assertTrue(condition, message);
            ExtentManager.getTest().pass("Assertion Passed: " + message);
        } catch (AssertionError e) {
            ExtentManager.getTest().fail("Assertion Failed: " + message);
            throw e;
        }
    }

    public static void fail(String message) {
        ExtentManager.getTest().fail("Assertion Failed: " + message);
        Assert.fail(message);
    }
}
