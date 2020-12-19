package com.example.demo.practice.juc.threadlocal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilSafe {

    private static final ThreadLocal<DateFormat> THREAD_LOCAL = ThreadLocal.withInitial(
            () ->new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    );

    public static Date parse(String dateStr){
        Date date = null;
        try{
            date = THREAD_LOCAL.get().parse(dateStr);
        }catch (ParseException e){
            e.printStackTrace();;
        }
        return date;
    }

}
