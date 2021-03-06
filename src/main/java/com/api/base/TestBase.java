package com.api.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
    public int RESPONSE_STATUS_CODE_200 = 200;
    public int RESPONSE_STATUS_CODE_500 = 500;
    public int RESPONSE_STATUS_CODE_400 = 400;
    public int RESPONSE_STATUS_CODE_401 = 401;
    public int RESPONSE_STATUS_CODE_201 = 201;

    public Properties prop;
    public TestBase(){
        try{
            prop = new Properties();
            String dirPath = System.getProperty("user.dir");
            String configFileLoc = "\\src\\main\\resources\\config.properties" ;
            FileInputStream ip = new FileInputStream(dirPath+configFileLoc);
            prop.load(ip);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
