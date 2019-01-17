package com.example.demo.test.readConfigFile;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 * 已验证
 * 核心代码  URL url = ClassLoader.getSystemResource(str);
 * 首先会去读str，如果没有读取到，会去读target/classes/keycenter.core.properties
 */
@Slf4j
public class KeyCenterConstants {

    public static String KEY_CENTER_URL;

    public static String KEY_CENTER_AK;

    public static String KEY_CENTER_SK;

    // 获取KeyCenter配置文件信息
    public static void getKeyCenterProperties(String str){
        // 获取配置文件操作对象
        Properties props = new Properties();
        try{
            FileInputStream fileInputStream = getInputStream(str);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            // 加载配置文件
            props.load(bufferedReader);
            // 通过参数获取配置
            KEY_CENTER_URL = props.getProperty("keyCenter.url");
            KEY_CENTER_AK = props.getProperty("keyCenter.ak");
            KEY_CENTER_SK = props.getProperty("keyCenter.sk");
            log.info(".............");
            log.info("KEY_CENTER_URL,{}", KEY_CENTER_URL);
        } catch (FileNotFoundException e) {
            log.error("error无法找到文件", e);
        } catch (IOException e) {
            log.error("error读文件出错",e);
        }
    }

    public static FileInputStream getInputStream(String str) throws FileNotFoundException {
        FileInputStream fileInputStream;
        // 通过配置文件获取流
        try {
            fileInputStream = new FileInputStream(str);
        } catch (FileNotFoundException e) {
            URL url = ClassLoader.getSystemResource(str);
            String filePath = url.getPath();
            fileInputStream = new FileInputStream(filePath);
        }
        return fileInputStream;
    }
}
