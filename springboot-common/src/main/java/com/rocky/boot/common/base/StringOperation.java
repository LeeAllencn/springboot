package com.rocky.boot.common.base;

import org.springframework.util.Base64Utils;

import java.io.UnsupportedEncodingException;

/**
 * @Author: Rocky
 * @Description: 字符串常用操作
 * @Date: Created in 13:49 2018/6/23
 */
public class StringOperation {

    public static void main(String[] args) {

        String str = "1.面朝大海，春暖花开;2.I want to go home,I want to go wuhan";
        String str1 = "1.面朝大海，春暖花开;2.I want to go home,I want to go wuhan";
        String str2 = "1.面朝大海，春暖花开;2.I want to GO home,I want to GO wuhan";

        //1.字符串查找:indexOf() and lastIndexOf()
        int index = str.indexOf("go");
        System.out.println(index);

        //2.获取子字符串:substring()
        String subStr = str.substring(24, 26);
        System.out.println(subStr);

        //3.忽略字符串的前导空格和尾部空格:trim()
        String trim_str = str.trim();
        System.out.println(trim_str);

        //4.字符串替换：replace() and replaceAll() and 正则表达式的应用
        String newStr = str.replace("go", "GO");
        System.out.println(newStr);
        String newStr1 = str.replaceAll("[^0-9]", "");
        System.out.println(newStr1);

        //5.判断字符串是否相等:equals() and equalsIgnoreCase()(忽略字符大小写比较)
        Boolean flag1 = str.equals(str1);
        System.out.println(flag1);
        Boolean flag2 = str.equalsIgnoreCase(str2);
        System.out.println(flag2);

        //6.按字典顺序比较两个字符串:compareTo()
        int flag = str.compareTo(str2);
        System.out.println(flag);

        //7.字母大小写切换:toLowerCase() and toUpperCase()
        String str_upper = str.toUpperCase();
        System.out.println(str_upper);

        //8.字符串分割:split()
        String[] str_split = str.split(";");
        System.out.println(str_split.length);
        for (String var : str_split) {
            System.out.println(var);
        }

        StringBuffer sb = new StringBuffer();

        //1.拼接字符串
        sb.append("I ").append("am ").append("Rocky!");
        System.out.println(sb);

        //2.清空StringBuffer
        sb.setLength(0);
        System.out.println(sb.length());

        // 字符串的base64转码和解码
        String source = "rocky";
        try {
            String target = new String(Base64Utils.encode(source.getBytes()), "UTF-8");
            source = new String(Base64Utils.decode(target.getBytes()), "UTF-8");
            System.out.println(target);
            System.out.println(source);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
