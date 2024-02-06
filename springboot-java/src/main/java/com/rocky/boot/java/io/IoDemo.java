package com.rocky.boot.java.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author : rocky
 * @date : created in 2024/2/6
 */
public class IoDemo {

    public static void main(String[] args) throws IOException {
        // 1.FileInputStream类和FileOutputStream
        // .dat命名二进制文件
        // 输出流，写操作
        int num = 10;
        FileOutputStream output = new FileOutputStream("springboot-java/temp.dat");
        for(int i = 1;i <= num;i ++){
            output.write(i);
        }
        output.close();

        // 输入流，读操作
        FileInputStream input = new FileInputStream("springboot-java/temp.dat");
        int value;
        while((value = input.read()) != -1){
            System.out.print(value + " ");
        }
        input.close();
    }
}
