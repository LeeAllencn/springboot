package com.rocky.boot.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Rocky on 2017-10-10.
 */
public class HttpUtil {
    public static String postRequest(String urlStr, String params) {
        BufferedReader in = null;
        PrintWriter printWriter = null;
        StringBuilder sb = new StringBuilder();
        URL url = null;
        HttpURLConnection conn = null;
        try {
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            printWriter = new PrintWriter(conn.getOutputStream());
            printWriter.write(params);
            printWriter.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String str = null;
            while ((str = in.readLine()) != null) {
                sb.append(str);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                if (conn != null) {
                    conn.disconnect();
                }
                if (in != null) {
                    in.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static void main(String[] args){
        try {
            String res = HttpUtil.postRequest("http://10.70.77.90:8000/registry/hub.hcpaas.com/namespace/wengkai16/image/wengkaibolg/tag/20170321091505","");
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
