package com.example.demo.exercise.other;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class MyTest {
    public static void main(String[] args) {

        List<String> masterList = new ArrayList<String>();
        masterList.add("master01");
        masterList.add("master02");
        masterList.add("master03");
        masterList.add("master04");
        masterList.add("master05");
        masterList.add("master06");
        masterList.add("master07");
        masterList.add("master08");
        masterList.add("master09");
        masterList.add("master10");
        masterList.add("master11");
        log.info("masterList:{}",masterList);
        // 主子表批量插入
        int interval = 3;
        List<String> subList = new ArrayList<>();
        for (int i = 0; i < masterList.size(); i++){
            subList.add(masterList.get(i));
            if ((i > 0 && i % interval == 0) || i == masterList.size() - 1){
               log.info("处理的是：{}", subList);
                subList.clear();
            }
        }
    }
}
