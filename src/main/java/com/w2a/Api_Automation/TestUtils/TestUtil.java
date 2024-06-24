package com.w2a.Api_Automation.TestUtils;

import com.w2a.Api_Automation.setUp.APISetUp;
import io.restassured.specification.RequestSpecification;

import java.util.Hashtable;

public class TestUtil extends APISetUp {

    public static RequestSpecification setFormParameter(String arguments, RequestSpecification request)
    {
        String arr[]=arguments.split(",");

        for(String ar:arr)
        {
            String[] a=ar.split(":");

            request.formParam(a[0],a[1]);
        }
        return request;
    }
}
