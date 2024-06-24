package com.w2a.Api_Automation.testcases;

import com.w2a.Api_Automation.API.CustomerAPI;
import com.w2a.Api_Automation.TestUtils.DataProviderClass;
import com.w2a.Api_Automation.TestUtils.TestUtil;
import com.w2a.Api_Automation.setUp.APISetUp;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class TestCustomerAPI extends APISetUp {

    @Test(dataProviderClass = DataProviderClass.class, dataProvider = "dp", priority = 1, enabled = true)
    public void validateCreateCustomerAPIWithValidData(Hashtable<String, String> data) {

        testLevelLog.get().assignAuthor("Rahul");
        testLevelLog.get().assignCategory("Regression");

        /*System.out.println("response from data provider:" + data.get("email") + "----" + data.get("description"));

        RequestSpecification request = setRequestSpecification().formParam("email", data.get("email")).formParam("description", data.get("description"));

        TestUtil.setFormParameter(data.get("arguments"),request);
        Response response = request.post("customers");

        testLevelLog.get().info(response.asString());

        System.out.println("----------------------------------------");

        //fetch the email from the response

        String emailInTheResponse = response.path("email");
        System.out.println("EMail in the response-------->" + emailInTheResponse);

        String descriptionInTheResponse = response.path("description");
        System.out.println("description in the response-------->" + descriptionInTheResponse);

        String footerInTheResponse = response.path("invoice_settings.footer");
        System.out.println("footer in the response-------->" + footerInTheResponse);

        JsonPath jsonPath = new JsonPath(response.asString());
        System.out.println("EMail using JsonPath-------->" + jsonPath.get("email"));

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 200);*/

        Response response= CustomerAPI.sendPostRequestToCreateCustomer(data);

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),201);
        Assert.assertEquals(response.path("email"),"sactest@gmail.com");
        Assert.assertEquals(response.statusLine(),"HTTP/1.1 200 OK");


    }

    @Test(dataProviderClass = DataProviderClass.class, dataProvider = "dp", priority = 0, enabled = false)
    public void validateCreateCustomerAPIWithInValidAuthKey(Hashtable<String,String> data) {

        testLevelLog.get().assignAuthor("Rahul");
        testLevelLog.get().assignCategory("Regression");

        RequestSpecification request=RestAssured.given();
        request.auth().basic(configProperty.getSecretKey(),"").formParam(data.get("email")).formParam(data.get("description"));

        Response response=request.post(data.get("endpoint"));

        Assert.assertEquals(response.getStatusCode(),201);


    }

    @Test(dataProviderClass = DataProviderClass.class, dataProvider = "dp", enabled = false, priority = 2)
    public void validateCreateCustomerAPIWithInValidEMail() {

    }

    @Test(dataProviderClass = DataProviderClass.class, dataProvider = "dp", priority = 0, enabled = false)
    public void validateCreateCustomerAPIWithoutPassingAuthKey() {

    }

    @Test(dataProviderClass = DataProviderClass.class, dataProvider = "dp", priority = 0, enabled = false)
    public void validateCreateCustomerAPIWithInvalidField() {

    }
}

