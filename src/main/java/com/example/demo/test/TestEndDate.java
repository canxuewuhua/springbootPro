package com.example.demo.test;

import com.example.demo.util.DateUtil;

import java.util.Date;

public class TestEndDate {
    public static void main(String[] args) {
/**
 *   与某个时间进行相比
 *   2019-05-03
     2019-05-03 09:06:49
     2019-05-03 21:30:00
     小于夜晚九点半
 */
        Date currentTimeTemp = new Date();
        String currentTimeFormatYYMMDD = DateUtil.formatDate(currentTimeTemp, DateUtil.FORMAT_PATTERN_DAY);
        String currentTimeFormatTime = DateUtil.formatDate(currentTimeTemp, DateUtil.FORMAT_PATTERN_TIME);
        String limitedTimeMinTemp = currentTimeFormatYYMMDD + " 21:30:00";
        System.out.println(currentTimeFormatYYMMDD);
        System.out.println(currentTimeFormatTime);
        System.out.println(limitedTimeMinTemp);
        if (DateUtil.compareTime(currentTimeFormatTime, limitedTimeMinTemp) >= 0) {
            //return ResultUtils.fail(CodeMsg.REPAYMENT_PROHIBITION_OF_REPAYMENT_PERIOD);
            System.out.println("大于夜晚九点半");
        }else{
            System.out.println("小于夜晚九点半");
        }
    }
}
