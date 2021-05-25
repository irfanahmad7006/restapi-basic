package com.api.utils;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TestUtils {


    public static String getValueByJPath(JSONObject responsejson, String jpath){
        Object obj = responsejson;
        for(String s : jpath.split("/"))
            if(!s.isEmpty())
                if(!(s.contains("[") || s.contains("]")))
                    obj = ((JSONObject) obj).get(s);
                else if(s.contains("[") || s.contains("]"))
                    obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
        return obj.toString();
    }

    public static Map<String,String> getHeaderMap(CloseableHttpResponse closeableHttpResponse){
        Header[] headers = closeableHttpResponse.getAllHeaders();
        HashMap<String,String> headersHashMap = new HashMap<>();
        for (Header a: headers){
            headersHashMap.put(a.getName(),a.getValue());
        }
        return headersHashMap;
    }


}
