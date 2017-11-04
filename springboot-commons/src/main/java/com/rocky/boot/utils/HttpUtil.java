package com.rocky.boot.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by Rocky on 2017-10-10.
 */
public class HttpUtil {

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = "";
            if(param == null) {
                urlNameString = url;
            }else {
                urlNameString = url + "?" + param;
            }
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

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
            //String res = HttpUtil.postRequest("http://10.70.77.90:8000/registry/hub.hcpaas.com/namespace/wengkai16/image/wengkaibolg/tag/20170321091505","");
            String res = HttpUtil.sendGet("http://wthrcdn.etouch.cn/weather_mini", "city=北京");
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
