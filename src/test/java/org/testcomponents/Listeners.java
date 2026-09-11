package org.testcomponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.ExtentReporterNG;

import java.io.File;
import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {

    ExtentReports extent = ExtentReporterNG.configReport();
    ExtentTest test;
    ThreadLocal<ExtentTest> thread= new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result) {
        //ITestListener.super.onTestStart(result);
        System.out.println("====== LISTENER ON TEST Start ======");
        test = extent.createTest(result.getMethod().getMethodName());
        thread.set(test);
        System.out.println("====== LISTENER ON TEST start END======");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
     //   ITestListener.super.onTestSuccess(result);
        thread.get().log(Status.PASS,result.getName()+" test passed");
        System.out.println("====== LISTENER ON TEST SUCCESS ======");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        //ITestListener.super.onTestFailure(result);
        System.out.println("====== LISTENER ON TEST fail ======");
        String path;
        thread.get().fail(result.getThrowable());
        try {
            driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            path = getScreenshot(result.getMethod().getMethodName(),driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        thread.get().addScreenCaptureFromPath(path,result.getMethod().getMethodName());
        System.out.println("====== LISTENER ON TEST fail end ======");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        //ITestListener.super.onFinish(context);
        extent.flush();
    }
}
