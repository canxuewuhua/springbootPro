package com.example.demo.test;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ZHUYONGQIANG on 2018/9/29.
 */
public class Test {
    public static void main(String[] args) throws ParseException {
        Double d1 = new Double("23") ;
        Double d2 = new Double("5") ;
        System.out.println(d1/d2);

        Date date = new Date();
        //System.out.println(DateUtil.formatDate(date,"yyyy-MM"));
        //System.out.println(DateUtil.parseDate(DateUtil.formatDate(new Date(), DateUtil.FORMAT_PATTERN_DAY), DateUtil.FORMAT_PATTERN_DAY));

        System.out.println(getNowDateShort());
    }

    public static Date getNowDateShort() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowdayTime = dateFormat.format(new Date());
        Date nowDate = dateFormat.parse(nowdayTime);
        return nowDate;
    }
}
