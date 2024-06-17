package com.w2a.Api_Automation.setUp;

import com.w2a.Api_Automation.TestUtils.ConfigProperty;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class APISetUp {
    public static ConfigProperty configProperty;

    @BeforeSuite
    public  void beforeSuite()
    {

        configProperty= ConfigFactory.create(ConfigProperty.class);

        RestAssured.baseURI=configProperty.getBaseURI();
        RestAssured.basePath=configProperty.getBasePath();
    }
    @BeforeMethod
    public void beforeMethod(Method method)
    {

        System.out.println("Test case:-"+ method.getName()+"execution started");

    }
    @AfterMethod
    public void afterMethod(ITestResult result)
    {
        if (result.getStatus()== ITestResult.SUCCESS)
        {
            System.out.println("Test case is passed");
        } else if (result.getStatus()==ITestResult.FAILURE) {
            System.out.println("Test case is failed");
        }
        else if (result.getStatus()==ITestResult.SKIP) {
            System.out.println("Test case is skipped");
        }
    }

    public  void afterSuite()
    {


    }
    public static RequestSpecification setRequestSpecification()
    {
        RequestSpecification request=RestAssured.given();
        request.auth().basic(configProperty.getSecretKey(),"");

        return request;
    }
}
