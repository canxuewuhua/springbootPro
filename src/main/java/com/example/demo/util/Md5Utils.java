package com.example.demo.util;

import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

/**
 * Created by daven on 2018/02/28.
 */
public class Md5Utils {
    public static String getMsg32(String message){
        StringBuffer sb = encryptData(message);
        return sb.toString();
    }
    public static String getMsg16(String message){
        StringBuffer sb = encryptData(message);
        return sb.toString().substring(8, 24);
    }

    public static StringBuffer encryptData(String message) {
        StringBuffer sb = new StringBuffer();
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(message.getBytes("UTF-8"));

            byte[] byteArray = messageDigest.digest();
            for (int i = 0; i < byteArray.length; i++)
            {
                if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                    sb.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
                else
                    sb.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return sb;
    }
    
    public static String fileMD5(String inputFile) {
        // 缓冲区大小（这个可以抽出一个参数）
        int bufferSize = 256 * 1024;
        FileInputStream fileInputStream = null;
        DigestInputStream digestInputStream = null;
        try {
            // 拿到一个MD5转换器（同样，这里可以换成SHA1）
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 使用DigestInputStream
            fileInputStream = new FileInputStream(inputFile);
            digestInputStream = new DigestInputStream(fileInputStream,messageDigest);
            // read的过程中进行MD5处理，直到读完文件
            byte[] buffer =new byte[bufferSize];
            while (digestInputStream.read(buffer) > 0);
            // 获取最终的MessageDigest
            messageDigest= digestInputStream.getMessageDigest();
            // 拿到结果，也是字节数组，包含16个元素
            byte[] resultByteArray = messageDigest.digest();
            // 同样，把字节数组转换成字符串
            return byteArrayToHex(resultByteArray);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                digestInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
            try {
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }
    
    public static String byteArrayToHex(byte[] byteArray) {
        
        // 首先初始化一个字符数组，用来存放每个16进制字符
        char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };
        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
        char[] resultCharArray =new char[byteArray.length * 2];
        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b& 0xf];
        }
        
        // 字符数组组合成字符串返回
        return new String(resultCharArray);
    }
    
    public static void main(String[] args){
//        System.out.println(Md5Utils.fileMD5("F:/download/GeoIP2-City_20170725.tar.gz"));
        System.out.println(Md5Utils.encryptData("1111111"));
    }
}
