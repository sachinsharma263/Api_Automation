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
import java.util.List;

public class TestCreateUserAPI extends APISetUp {

@Test(dataProviderClass = DataProviderClass.class, dataProvider = "dp")
public void validateCreateCustomerAPIWithValidData(Hashtable<String,String> data)
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
@Test(dataProviderClass = DataProviderClass.class,dataProvider ="dp",enabled = false)
public void fetchCustomer(Hashtable<String,String> data)
{

    String endPoint=data.get("endPoint");
    RequestSpecification specRequest=setRequestSpecification().log().all();
   Response response= specRequest.request(data.get("methodType"),data.get("endPoint"));

   response.prettyPrint();

//   List<String> listOfId=response.path("data.id");
//
//   System.out.println(listOfId);
//  int size=response.path("data.size()");
//    System.out.println(size);
//
//  size= response.path("data[0].size()");
//    System.out.println(size);
    // "default_source": "card_1PU1slJAJfZb9HEBokuQQMrp",

    //List<String> listOfData=response.path("data");
    int length=response.path("data.size()");

    for (int i=0;i<length;i++)
    {
        String actualDefaultSource=response.path("data["+i+"].default_source");
        String expectedDefaultSource="card_1PU1slJAJfZb9HEBokuQQMrp";
        if (actualDefaultSource.equalsIgnoreCase(expectedDefaultSource))
        {

            String customerId=response.path("data["+i+"].id");
            System.out.println("customerId:"+ customerId);
            break;
        }
    }
}
}
