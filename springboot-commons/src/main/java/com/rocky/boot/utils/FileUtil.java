package com.rocky.boot.utils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
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
}
