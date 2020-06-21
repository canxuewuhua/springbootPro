package com.example.demo.test;

public class StringTest {
    public static void main(String[] args) {
        String yearMonth = "2017-06";
        // 2
        System.out.println(yearMonth.substring(0,1));
        System.out.println(yearMonth.substring(5,7));
        int yearMonthStr = Integer.valueOf(yearMonth.substring(5,7))-1;
        System.out.println(yearMonthStr);


        String validDate1 = "2005.01.06-2025.01.06";
        String validDate2 = "2005.01.01-长期";
        System.out.println(validDate1.length());
        System.out.println(validDate2.length());

        System.out.println(validDate1.substring(validDate1.length() -10, validDate1.length()));
        System.out.println(validDate2.substring(validDate2.length() -2, validDate2.length()));



    }
}
