package com.example.demo.util;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class SerialNumberUtil {
    /**
     * 针对每个表对应的主键ID，现在是通过UUID生成的
     */
    public static String getPrimaryId() {
        return UUID.randomUUID().toString();
    }

}
