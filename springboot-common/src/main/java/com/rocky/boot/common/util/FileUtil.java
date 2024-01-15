package com.rocky.boot.common.util;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author rocky
 * Created by Rocky on 2017-10-10.
 */
public class FileUtil {

    /**
     * 文件下载
     *
     * @param fileName 名称
     * @param filePath 路径
     * @param response 响应
     * @throws IOException io异常
     */
    public static void downloadFile(String fileName, String filePath, HttpServletResponse response) throws IOException {
        response.reset();
        response.setContentType("application/x-download");
        response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("GBK"), StandardCharsets.ISO_8859_1));
        File f = new File(filePath);
        try (ServletOutputStream out = response.getOutputStream(); FileInputStream in = new FileInputStream(f)) {
            byte[] e = new byte[1024];

            int i;
            while ((i = in.read(e)) > 0) {
                out.write(e, 0, i);
            }
            out.flush();
        } catch (Exception var11) {
            var11.printStackTrace();
        }
    }

    /**
     * 文件拷贝(采用FileInputStream和FileOutputStream批量读写效果好)
     *
     * @param srcFile 源文件
     * @param destFile 目标文件
     * @throws IOException 异常
     */
    public static void copyFile(File srcFile, File destFile) throws IOException {
        if (!srcFile.exists()) {
            throw new IllegalArgumentException("文件" + srcFile + "不存在");
        }
        if (!srcFile.isFile()) {
            throw new IllegalArgumentException(srcFile + "不是一个文件");
        }
        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(destFile);
        byte[] buf = new byte[8 * 1024];
        int b;
        while ((b = fis.read(buf, 0, buf.length)) != -1) {
            fos.write(buf, 0, b);
            fos.flush();
        }
        fis.close();
        fos.close();
    }
}
