package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Properties;

@Slf4j
public class Configuration {
    private Properties resource = new Properties();

    public Configuration(String resourceName) {
        this.load(resourceName);
    }


    /**
     * 优先读取jar包内文件，若无则读取文件系统
     */
    public void load(String resourceName) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resourceName);
        if (inputStream == null) {
            try {
                inputStream = new FileInputStream(new File(resourceName));
            } catch (FileNotFoundException ex) {
                log.error("load resource file from file system failed!", ex);
            }
        }

        try {
            resource.load(inputStream);
        } catch (IOException e) {
            log.error("load resource content failed!", e);
        }

        try {
            inputStream.close();
        } catch (IOException e) {
            log.error("close InputStream failed!", e);
        }
    }

    public String get(String keyString) {
        if (keyString == null) {
            return null;
        }

        if (this.resource.containsKey(keyString)) {
            return this.resource.getProperty(keyString);
        }

        return null;
    }
}
