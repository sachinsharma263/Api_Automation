package com.w2a.Api_Automation.API;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.w2a.Api_Automation.TestUtils.ConfigProperty;
import com.w2a.Api_Automation.TestUtils.ExcelReader;
import com.w2a.Api_Automation.TestUtils.Extentmanager;
import com.w2a.Api_Automation.TestUtils.TestUtil;
import com.w2a.Api_Automation.setUp.APISetUp;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Hashtable;

public class CustomerAPI extends APISetUp {

    public static Response sendPostRequestToCreateCustomer(Hashtable<String,String> data)
    {

        RequestSpecification request=TestUtil.setFormParameter(data.get("arguments"),setRequestSpecification());
        return request.post("customers");

    }
}
