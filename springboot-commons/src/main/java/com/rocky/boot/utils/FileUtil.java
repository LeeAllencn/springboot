package com.rocky.boot.utils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Rocky on 2017-10-10.
 */
public class FileUtil {

    public static void downloadFile(String fileName, String filePath, HttpServletResponse response) throws IOException {
        response.reset();
        response.setContentType("application/x-download");
        response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("GBK"), "ISO-8859-1"));
        ServletOutputStream outp = null;
        FileInputStream in = null;
        File f = new File(filePath);
        try {
            outp = response.getOutputStream();
            in = new FileInputStream(f);
            byte[] e = new byte[1024];
            boolean i = false;

            int i1;
            while((i1 = in.read(e)) > 0) {
                outp.write(e, 0, i1);
            }
            outp.flush();
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            if(in != null) {
                in.close();
                in = null;
            }
            if(outp != null) {
                outp.close();
                outp = null;
            }
        }
    }

    //文件拷贝(采用FileInputStream和FileOutputStream批量读写效果好)
    public static void copyFile(File srcFile,File destFile) throws IOException{
        if(!srcFile.exists()){
            throw new IllegalArgumentException("文件" + srcFile + "不存在");
        }
        if(!srcFile.isFile()){
            throw new IllegalArgumentException(srcFile + "不是一个文件");
        }
        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(destFile);
        byte[] buf = new byte[8 * 1024];
        int b;
        while((b = fis.read(buf, 0, buf.length)) != -1){
            fos.write(buf, 0, b);
            fos.flush();
        }
        fis.close();
        fos.close();
    }

    public static void main(String[] args) throws IOException {
        File srcFile = new File("C:\\Users\\lenovo\\Desktop\\备注.txt");
        File destFile = new File("C:\\Users\\lenovo\\Desktop\\test.txt");
        FileUtil.copyFile(srcFile, destFile);
    }
}
