package com.example.demo.test;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
@Slf4j
public class ListTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
//        list.add(0);
//        list.add(0);
//        list.add(0);
//        list.add(0);
//        Set s = new HashSet(list);
//        if (s.size()==1) {
//            System.out.println("都是零");
//        }else {
//            System.out.println("不是都为零");
//        }
//        int maxDay = Collections.max(list);
//        System.out.println(maxDay);
        int i=5;
        int m;
        String str = "Hello";
        try {
            m= i/0;
        } catch (Exception e) {
            log.error("更新出资方式异常:{},{}", e.getMessage(),str, e);
        }
    }
}
