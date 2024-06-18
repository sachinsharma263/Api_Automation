package com.w2a.Api_Automation.setUp;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.w2a.Api_Automation.TestUtils.ConfigProperty;
import com.w2a.Api_Automation.TestUtils.ExcelReader;
import com.w2a.Api_Automation.TestUtils.Extentmanager;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class APISetUp {
    public static ConfigProperty configProperty;

   public static ExcelReader excel=new ExcelReader(System.getProperty("user.dir")+"/src/test/resources/testData/TestData.xlsx");

   public static ExtentReports extentReports;

   public static ThreadLocal<ExtentTest> classLevelLog=new ThreadLocal<ExtentTest>();
    public static ThreadLocal<ExtentTest> testLevelLog=new ThreadLocal<ExtentTest>();
    @BeforeSuite
    public  void beforeSuite()
    {

        configProperty= ConfigFactory.create(ConfigProperty.class);

        RestAssured.baseURI=configProperty.getBaseURI();
        RestAssured.basePath=configProperty.getBasePath();
        extentReports=Extentmanager.GetExtent(configProperty.getTestFilePath()+configProperty.getTestReportName());
    }
    @BeforeClass
    public void beforeClass()
    {

        ExtentTest classLevelTest=extentReports.createTest(getClass().getSimpleName());
        classLevelLog.set(classLevelTest);

    }
    @BeforeMethod
    public void beforeMethod(Method method)
    {

        ExtentTest test=classLevelLog.get().createNode(method.getName());
        testLevelLog.set(test);
        testLevelLog.get().info("Test case:-"+ method.getName()+"execution started");
        System.out.println("Test case:-"+ method.getName()+"execution started");

    }
    @AfterMethod
    public void afterMethod(ITestResult result)
    {
        if (result.getStatus()== ITestResult.SUCCESS)
        {
            testLevelLog.get().pass("Test case passed");
            System.out.println("Test case is passed");
        } else if (result.getStatus()==ITestResult.FAILURE) {
            testLevelLog.get().pass("Test case failed");
            System.out.println("Test case is failed");
        }
        else if (result.getStatus()==ITestResult.SKIP) {
            testLevelLog.get().pass("Test case skipped");
            System.out.println("Test case is skipped");
        }
        extentReports.flush();
    }

    public  void afterSuite()
    {


    }
    public static RequestSpecification setRequestSpecification()
    {
        RequestSpecification request=RestAssured.given();
        request.auth().basic(configProperty.getSecretKey(),"");
        testLevelLog.get().pass("Request Specification set");

        return request;
    }
}
