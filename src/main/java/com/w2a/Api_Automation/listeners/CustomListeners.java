package com.w2a.Api_Automation.listeners;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.w2a.Api_Automation.setUp.APISetUp;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class CustomListeners extends APISetUp implements ITestListener{

    public void onFinish(ITestContext arg0) {
        // TODO Auto-generated method stub

    }

    public void onStart(ITestContext arg0) {
        // TODO Auto-generated method stub

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        // TODO Auto-generated method stub

    }

    public void onTestFailure(ITestResult arg0) {
        System.out.println("Inside the failed method");
        String exceptionMessage= Arrays.toString(arg0.getThrowable().getStackTrace());
        testLevelLog.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
                + "</font>" + "</b >" + "</summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details>"
                + " \n");
        String failureLogg = "This Test case got Failed";
        Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
        testLevelLog.get().log(Status.FAIL, m);

        extentReports.flush();

    }

    public void onTestSkipped(ITestResult arg0) {
        System.out.println("Inside the skip method");
		/* test = classLevelLog.get().createNode(arg0.getName());
		testLevelLog.set(test);*/
        testLevelLog.get().skip("This Test Case got Skipped-"+arg0.getName());
        extentReports.flush();

    }

    public void onTestStart(ITestResult arg0) {
        System.out.println("Inside the start method");

        testLevelLog.get().info("<b>"+"Test Case:- " + arg0.getName() + " execution started"+"</b>");


    }

    public void onTestSuccess(ITestResult arg0) {
        System.out.println("Inside the success method");
        testLevelLog.get().pass("<b>"+"This Test Case got Passed"+"</b>");
        extentReports.flush();

    }

}

