package com.example.demo.exercise.valid_bank_card;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidAccountNoTest {

    public static void main(String[] args) {

//        String firstRelativeName = "0张三 丰";
//        firstRelativeName = firstRelativeName.replaceAll("[^\u4E00-\u9FA5]", "");
//        System.out.println(firstRelativeName);

//        System.out.println(validBankNum("6222620140003439"));
//        System.out.println(validBankNum("6222600260001072444"));

        System.out.println(validateAccountNo("6222600260001072444"));

    }

    public static  void validateCardNo(){
        String phone = "11111112056";
        String regex = "^1(3|4|5|6|7|8|9)\\d{9}$";
        if(phone.length() != 11){
            System.out.println("手机号应为11位数");
        }else{
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if(isMatch){
                System.out.println("您的手机号" + phone + "是正确格式@——@");
            } else {
                System.out.println("您的手机号" + phone + "是错误格式！！！");
            }
        }
    }

    public static Boolean validateAccountNo(String accountNo){
        if (validBankNum(accountNo)){
            return matchLuhn(accountNo);
        }else{
            System.out.println("该银行卡号不符合首字符为数字或者不满足16-19位全数字的规定");
            return false;
        }
    }

    public static Boolean validBankNum(String idCard) {
//        String regex = "^([1-9]{1})(\\d{14}|\\d{15}|\\d{18})$";
        String regex = "^([1-9]{1})(\\d{15,18})$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(idCard);
        boolean isMatch = m.matches();
        return isMatch;
    }

    /**
     * 匹配Luhn算法：可用于检测银行卡卡号
     * @param cardNo
     * @return
     */
    public static boolean matchLuhn(String cardNo) {
        int[] cardNoArr = new int[cardNo.length()];
        for (int i=0; i<cardNo.length(); i++) {
            cardNoArr[i] = Integer.valueOf(String.valueOf(cardNo.charAt(i)));
        }
        for(int i=cardNoArr.length-2;i>=0;i-=2) {
            cardNoArr[i] <<= 1;
            cardNoArr[i] = cardNoArr[i]/10 + cardNoArr[i]%10;
        }
        int sum = 0;
        for(int i=0;i<cardNoArr.length;i++) {
            sum += cardNoArr[i];
        }
        return sum % 10 == 0;
    }
}
