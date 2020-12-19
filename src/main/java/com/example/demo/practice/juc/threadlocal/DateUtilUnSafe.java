package com.example.demo.practice.juc.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilUnSafe {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date parse(String dateStr){
        Date date = null;
        try{
            date = sdf.parse(dateStr);
        }catch (ParseException e){
            e.printStackTrace();;
        }
        return date;
    }

}
