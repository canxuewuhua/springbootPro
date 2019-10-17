package com.example.demo.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BeanUtil {

    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Field fields[] = bean.getClass().getDeclaredFields();
            Field.setAccessible(fields, true);
            for (Field field : fields) {
                if (null != field.get(bean)) {
                    map.put(field.getName(), field.get(bean));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static <T> Map<String, String> beanToMapString(T bean) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            Field fields[] = bean.getClass().getDeclaredFields();
            Field.setAccessible(fields, true);
            for (Field field : fields) {
                if (null != field.get(bean)) {
                    map.put(field.getName(), (String) field.get(bean));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

}
