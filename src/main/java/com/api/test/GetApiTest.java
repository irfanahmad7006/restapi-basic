package com.api.test;

import com.api.base.TestBase;
import com.api.client.RestClient;
import com.api.utils.TestUtils;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetApiTest extends TestBase {
    TestBase testBase;
    String seriveUrl;
    String apiURL;
    String completeUrl;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;

    @BeforeMethod
    public void setProp() {
        testBase = new TestBase();
        seriveUrl = prop.getProperty("URL");
        apiURL = prop.getProperty("ServiceURL");
        completeUrl = seriveUrl + apiURL;
    }

    @Test
    public void getMethodTest() throws IOException {
        restClient = new RestClient();
        restClient.get(completeUrl);
    }

    @Test
    public void apiTest() throws IOException {
        restClient = new RestClient();
        closeableHttpResponse = restClient.getExecuteMethod(completeUrl);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status Code:-------> " + statusCode);
        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "This not not 200 status");
//        String resString = EntityUtils.toString(response.getEntity(), "UTF-8");
        String response = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        JSONObject jsonObject = new JSONObject(response);
        System.out.println(jsonObject);
        System.out.println("_____");

        String perPageValue = TestUtils.getValueByJPath(jsonObject, "per_page");
        System.out.println(perPageValue);
        Assert.assertEquals(perPageValue, "6");

        String totalValue = TestUtils.getValueByJPath(jsonObject, "total");
        System.out.println(totalValue);
        Assert.assertEquals(totalValue, "12");

        String firstName = TestUtils.getValueByJPath(jsonObject, "/data[0]/first_name");
        String lastName = TestUtils.getValueByJPath(jsonObject, "/data[0]/last_name");
        String id = TestUtils.getValueByJPath(jsonObject, "/data[0]/id");
        String avatar = TestUtils.getValueByJPath(jsonObject, "/data[0]/avatar");

        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(id);
        System.out.println(avatar);

        Map<String, String> headers = TestUtils.getHeaderMap(closeableHttpResponse);
        System.out.println(headers);
        Assert.assertEquals("chunked", headers.get("Transfer-Encoding"));
    }


    @Test
    public void getCallWithHeaders() throws IOException {
        restClient = new RestClient();
        HashMap<String, String> map = new HashMap<>();
        map.put("Content-Type","application/json");

        closeableHttpResponse =restClient.getExecuteMethod(completeUrl,map);
        String response = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        JSONObject jsonObject = new JSONObject(response);
    }
}
