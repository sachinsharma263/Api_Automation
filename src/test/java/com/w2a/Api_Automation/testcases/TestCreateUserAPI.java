package com.w2a.Api_Automation.testcases;

import com.w2a.Api_Automation.TestUtils.ConfigProperty;
import com.w2a.Api_Automation.TestUtils.DataProviderClass;
import com.w2a.Api_Automation.setUp.APISetUp;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class TestCreateUserAPI extends APISetUp {

@Test(dataProviderClass = DataProviderClass.class, dataProvider = "dp")
public void validateCreateCustomerAPIWithVD(Hashtable<String,String> data)
{

    //System.out.println("Execution of test case:- validateCreateCustomerAPIWithValidData started");


    //ConfigProperty configProperty= ConfigFactory.create(ConfigProperty.class);
    //RestAssured.baseURI=configProperty.getBaseURI();
    //RestAssured.basePath=configProperty.getBasePath();

//    RequestSpecification request=RestAssured.given().auth().basic(configProperty.getSecretKey(),"").
//            formParam("email","sac@gmail.com").formParam("description","testing stripe")
//            .log().all();

    testLevelLog.get().assignAuthor("Rahul");
    testLevelLog.get().assignCategory("Regression");
   // RequestSpecification request=setRequestSpecification(). formParam("email","sac@gmail.com").formParam("description","testing stripe");
//            .log().all();

    System.out.println("response from data provider:"+data.get("email")+"----"+data.get("description"));

    RequestSpecification request=setRequestSpecification(). formParam("email",data.get("email")).formParam("description",data.get("description"));

    Response response=request.post("customers");

    testLevelLog.get().info(response.asString());

    System.out.println("----------------------------------------");

    //fetch the email from the response

    String emailInTheResponse=response.path("email");
    System.out.println("EMail in the response-------->"+emailInTheResponse);

    String descriptionInTheResponse=response.path("description");
    System.out.println("description in the response-------->"+descriptionInTheResponse);

    String footerInTheResponse=response.path("invoice_settings.footer");
    System.out.println("footer in the response-------->"+footerInTheResponse);

    JsonPath jsonPath=new JsonPath(response.asString());
    System.out.println("EMail using JsonPath-------->"+jsonPath.get("email"));

    response.prettyPrint();

    Assert.assertEquals(response.getStatusCode(),200);


}
}
