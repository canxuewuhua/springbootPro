package com.example.demo.test;

import com.example.demo.util.DateUtil;

import java.util.Date;

public class TestEndDate {
    public static void main(String[] args) {

        Date currentTimeTemp = new Date();
        String currentTimeFormatYYMMDD = DateUtil.formatDate(currentTimeTemp, DateUtil.FORMAT_PATTERN_DAY);
        String currentTimeFormatTime = DateUtil.formatDate(currentTimeTemp, DateUtil.FORMAT_PATTERN_TIME);
        String limitedTimeMinTemp = currentTimeFormatYYMMDD + " 21:30:00";
        if (DateUtil.compareTime(currentTimeFormatTime, limitedTimeMinTemp) >= 0) {
            //return ResultUtils.fail(CodeMsg.REPAYMENT_PROHIBITION_OF_REPAYMENT_PERIOD);
            System.out.println("大于夜晚九点钟");
        }else{
            System.out.println("小于夜晚九点钟");
        }
    }
}
