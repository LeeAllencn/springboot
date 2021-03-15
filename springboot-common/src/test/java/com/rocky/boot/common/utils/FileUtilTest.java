package com.rocky.boot.common.utils;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author rocky
 * Description:
 * Created in 2021/3/15
 */
public class FileUtilTest {

    @Test
    public void downloadFile() {
    }

    @Test
    public void copyFile() throws IOException {
        File srcFile = new File("C:\\Users\\lenovo\\Desktop\\新建文本文档.txt");
        File destFile = new File("C:\\Users\\lenovo\\Desktop\\test.txt");
        FileUtil.copyFile(srcFile, destFile);
    }
}