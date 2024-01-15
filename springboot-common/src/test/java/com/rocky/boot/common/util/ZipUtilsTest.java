package com.rocky.boot.common.util;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author rocky
 * Description:
 * Created in 2021/3/17
 */
public class ZipUtilsTest {

    @Test
    public void toZip() throws FileNotFoundException {
        String srcDir = "C:\\Users\\Lenovo\\Desktop\\nginx-1.17.5";
        FileOutputStream fos = new FileOutputStream("d:/mytest01.zip");
        ZipUtils.toZip(srcDir, fos, true);
    }
}