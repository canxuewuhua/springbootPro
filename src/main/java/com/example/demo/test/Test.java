package com.example.demo.test;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ZHUYONGQIANG on 2018/9/29.
 */
public class Test {
    public static void main(String[] args) throws ParseException {
        System.out.println(addDays(new Date(), -1));
    }

    public static Date getNowDateShort() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowdayTime = dateFormat.format(new Date());
        Date nowDate = dateFormat.parse(nowdayTime);
        return nowDate;
    }

    /**
     * 获取指定日期增加指定日间隔后的日期
     *
     * @param date
     * @param interval
     * @return
     */
    public static Date addDays(Date date, int interval) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, interval);
        return cal.getTime();
    }

    /**
     * java对象序列化成字节数组
     *
     * @param object
     * @return
     */
    public static byte[] toBytes(Object object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } finally {
            try {
                oos.close();
            } catch (Exception e) {
            }
        }
    }
}
@Data
class User implements Serializable{
    private String name;
    private int age;
}
