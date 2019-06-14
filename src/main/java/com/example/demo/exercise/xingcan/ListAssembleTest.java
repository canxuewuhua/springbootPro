package com.example.demo.exercise.xingcan;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListAssembleTest {
    public static void main(String[] args) {
//        String MSG = "MSG";
//        String MSG1 = "MSG1";
//        String MSG2 = "MSG2";
//        String MSG3 = "MSG3";
//        if (MSG.equals("MSG")){
//            System.out.println("MSG");
//        }
//        if (MSG.equals("456")){
//            System.out.println("456");
//        }
//        if (MSG.equals("789")){
//            System.out.println("789");
//        }else{
//            System.out.println("LOAN");
//        }



        Map<String, String> map = new HashMap<>();
        map.put("name", "张三");
        if (StringUtils.isBlank("张三")){
            System.out.println("11111");
        }else{
            System.out.println("222322");
        }
    }
}
