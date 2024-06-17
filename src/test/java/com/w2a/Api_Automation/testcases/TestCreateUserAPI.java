package com.w2a.Api_Automation.testcases;

import com.w2a.Api_Automation.TestUtils.ConfigProperty;
import com.w2a.Api_Automation.setUp.APISetUp;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCreateUserAPI extends APISetUp {

 @Test
public void validateCreateCustomerAPIWithValidData()
{

    //System.out.println("Execution of test case:- validateCreateCustomerAPIWithValidData started");


    //ConfigProperty configProperty= ConfigFactory.create(ConfigProperty.class);
    //RestAssured.baseURI=configProperty.getBaseURI();
    //RestAssured.basePath=configProperty.getBasePath();

//    RequestSpecification request=RestAssured.given().auth().basic(configProperty.getSecretKey(),"").
//            formParam("email","sac@gmail.com").formParam("description","testing stripe")
//            .log().all();

    RequestSpecification request=setRequestSpecification(). formParam("email","sac@gmail.com").formParam("description","testing stripe");
//            .log().all();



    Response response=request.post("customers");

    System.out.println("----------------------------------------");
   // response.prettyPrint();

    Assert.assertEquals(response.getStatusCode(),201);


}
}
