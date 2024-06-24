package com.w2a.Api_Automation.testcases;

import com.w2a.Api_Automation.POJO.User;
import com.w2a.Api_Automation.TestUtils.DataProviderClass;
import com.w2a.Api_Automation.setUp.APISetUp;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class ReqResCreateUser extends APISetUp {

    @Test(dataProviderClass = DataProviderClass.class,dataProvider = "dp")
    public void validateCreateUserWithValidData(Hashtable<String,String> data)
    {
        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="/api";

        RequestSpecification request=RestAssured.given();

        User user=new User(data.get("name"),data.get("job"));

        request.contentType(ContentType.JSON).body(user).post("/users");


    }
}
