package com.rocky.boot.design.patterns.decorator.java;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author rocky
 * @Description: LowerCaseInputStream测试类
 * @Date: Created in 2018/12/11
 */
public class LowerCaseInputStreamTest {

    public static void main(String[] args) {
        int c;
        try {
            InputStream in =
                    new LowerCaseInputStream(
                            new BufferedInputStream(
                                    new FileInputStream(LowerCaseInputStreamTest.class.getResource("").getPath() + "test.txt")
                            )
                    );
            while ((c = in.read()) >= 0) {
                System.out.print((char)c);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
