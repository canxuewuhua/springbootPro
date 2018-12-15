package com.example.demo.test;

import java.util.Random;

public class Foo {
    public static void main(String[] args) {
        int group = 5;
        int men = 101;
        int groupTemp = group;
        Random r = new Random();
        int [] radomnum = new int[group];
        int[] indexs = new int[group];
        Integer[][] groups = new Integer[group][men%group>0?men/group+1:men/group];
        int[] manArray = new int[men];
        for(int i=0;i<group;i++) {
            radomnum[i] = i;
        }
        for(int i=0;i<manArray.length;i++) {
            manArray[i] = i+1;
        }
        for(int i=0;i<manArray.length;i++) {
            int index = r.nextInt(groupTemp--);
            int menIndex = r.nextInt(manArray.length-i);
            int temp = radomnum[index];
            groups[temp][indexs[temp]++]=manArray[menIndex];
            radomnum[index] = radomnum[groupTemp];
            radomnum[groupTemp] = temp;
            if(groupTemp==0) {groupTemp=group;}
            temp = manArray[menIndex];
            manArray[menIndex]=manArray[manArray.length-i-1];
            manArray[manArray.length-i-1] = temp;
        }
        for(int i=0;i<groups.length;i++) {
            System.out.print("第"+(i+1)+"组:\t");
            for(int j=0;j<groups[i].length;j++) {
                if(groups[i][j]!=null) {
                    System.out.print(groups[i][j]+"\t");
                }
            }
            System.out.println();
        }
    }
}