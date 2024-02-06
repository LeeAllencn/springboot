package com.rocky.boot.java.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author : rocky
 * @date : created in 2024/2/6
 */
public class IoTest {

    public static void main(String[] args) throws IOException {

        // 1.文件的编码
        String s = "小明abc";
        // 采用项目的默认编码utf8
        byte[] bytesDefault = s.getBytes();
        for (byte b : bytesDefault) {
            System.out.print(Integer.toHexString(b & 0xff) + " ");
        }
        System.out.println();

        // 显式采用编码格式utf8
        byte[] bytesUtf8 = s.getBytes(StandardCharsets.UTF_8);
        for (byte b : bytesUtf8) {
            System.out.print(Integer.toHexString(b & 0xff) + " ");
        }
        System.out.println();

        //采用编码gbk
        byte[] bytesGbk = s.getBytes("gbk");
        for (byte b : bytesGbk) {
            System.out.print(Integer.toHexString(b & 0xff) + " ");
        }
        System.out.println();

        // java是双字节编码，采用的是utf-16be
        byte[] bytesUtf16be = s.getBytes(StandardCharsets.UTF_16BE);
        for (byte b : bytesUtf16be) {
            System.out.print(Integer.toHexString(b & 0xff) + " ");
        }
        System.out.println();

        // 由字节序列构建字符串时的乱码呈现
        String strSuccess = new String(bytesGbk, "gbk");
        System.out.println(strSuccess);
        String strError = new String(bytesGbk, StandardCharsets.UTF_8);
        System.out.println(strError);

        // 2.文件目录加载
        // 加载当前类（IoTest.java）所在目录下的Person.java文件
        String filePath = IoTest.class.getResource("").getPath() + "Person.java";
        System.out.println(filePath);
    }
}
