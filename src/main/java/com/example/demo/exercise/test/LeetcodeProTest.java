package com.example.demo.exercise.test;

public class LeetcodeProTest {
    public static void main(String[] args) {
        String ss = "Let's take LeetCode contest";

        String[] strArr = ss.split(" ");
        String newStr ="";
        for (int i=0;i<strArr.length;i++){
            strArr[i] = (new StringBuffer(strArr[i])).reverse().toString();
            newStr += strArr[i] + " ";
        }
        System.out.println(newStr.trim());
    }
}
