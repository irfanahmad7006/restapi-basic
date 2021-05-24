package com.api.test;

import com.api.base.TestBase;
import com.api.client.RestClient;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetApiTest extends TestBase {
    TestBase testBase;
    String seriveUrl;
    String apiURL;
    String completeUrl;
    RestClient restClient;

    @BeforeMethod
    public void setProp() {
        testBase = new TestBase();
        seriveUrl = prop.getProperty("URL");
        apiURL = prop.getProperty("ServiceURL");
        completeUrl=seriveUrl+apiURL;
    }

    @Test
    public void getMethodTest() throws IOException {
        restClient = new RestClient();
        restClient.get(completeUrl);
    }
}
