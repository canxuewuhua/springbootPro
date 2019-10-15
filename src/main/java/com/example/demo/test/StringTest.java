package com.example.demo.test;

public class StringTest {
    public static void main(String[] args) {
        String yearMonth = "2017-06";
        // 2
        System.out.println(yearMonth.substring(0,1));
        System.out.println(yearMonth.substring(5,7));
        int yearMonthStr = Integer.valueOf(yearMonth.substring(5,7))-1;
        System.out.println(yearMonthStr);
    }
}
