package com.api.client;


import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestClient {
    // Create GET Method
    public void get(String url) throws IOException {
        //CloseableHttpClient is an Abstract class which implements HttpClients class
        //Creating a default connection
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //create a connection with the url
        HttpGet httpGet = new HttpGet(url);
        //hitting the url and save it in response variable
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //get status code from response variable
        int statusCode=response.getStatusLine().getStatusCode();
        System.out.println("Status Code is----> "+statusCode);
        //get headers from response variable
        Header[] allHeader = response.getAllHeaders();
        //print headers without using hashmap;
        for (Header a: allHeader){
            System.out.println(a.getName()+"----"+a.getValue());
        }
        //store headers using hashmap;
        HashMap<String,String> headerMap = new HashMap<>();
        for (Header a: allHeader){
            headerMap.put(a.getName(),a.getValue());
        }
        //print headers hashmap;
        System.out.println("Headers hasMap---> "+headerMap);
        //get response from response variable and change into string
        String resString = EntityUtils.toString(response.getEntity(), "UTF-8");
        System.out.println("Response in String----> "+ resString);
        //Convert response String into Json object
        JSONObject resJsonObject = new JSONObject(resString);
        System.out.println("Response in Json Object----> "+ resJsonObject);
    }
}
