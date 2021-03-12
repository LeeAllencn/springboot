package com.rocky.boot.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author rocky
 * Created by Rocky on 2017-10-10.
 */
public class OkHttpRestUtil {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient client = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES).writeTimeout(5, TimeUnit.MINUTES).build();

    /**
     * get request
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static Map<?, ?> get(String url, String token) throws IOException {
        Request request = new Request.Builder()
                .addHeader("Authorization", token)
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        ObjectMapper mapper = new ObjectMapper();
        Map<?, ?> map = mapper.readValue(response.body().string(), Map.class);
        return map;
    }

    /**
     * post request
     * @param url
     * @param token
     * @param obj
     * @return
     * @throws IOException
     */
    public static Map<?, ?> post(String url, String token, Object obj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(obj);
        RequestBody body = RequestBody.create(json, MediaType.parse("application/json;charset=UTF-8"));
        Request request = new Request.Builder()
                .addHeader("Authorization", token)
                .url(url)
                .post(body)
                .build();
        System.out.println("------------------" + client.connectTimeoutMillis() / 1000 + "--------------------------" + client.readTimeoutMillis() / 1000);
        Response response = client.newCall(request).execute();
        Map<?, ?> map = mapper.readValue(response.body().string(), Map.class);
        return map;
    }

    public static Map<?, ?> post(String url, String token, String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + token)
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        Map<?, ?> resultMap = mapper.readValue(response.body().string(), Map.class);
        return resultMap;
    }

    /**
     * put request
     * @param url
     * @param token
     * @param obj
     * @return
     * @throws IOException
     */
    public static Map<?, ?> put(String url, String token, Object obj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(obj);
        RequestBody body = RequestBody.create(content, MediaType.parse("application/json;charset=UTF-8"));
        Request request = new Request.Builder()
                .addHeader("Authorization", token)
                .url(url)
                .put(body)
                .build();
        Response response = client.newCall(request).execute();
        String resBody = response.body().string();
        Map<?, ?> map = null;
        if (StringUtils.isNotEmpty(resBody)) {
            map = mapper.readValue(resBody, Map.class);
        }
        return map;
    }

    /**
     * delete request
     * @param url
     * @param token
     * @return
     * @throws IOException
     */
    public static Map<?, ?> delete(String url, String token) throws IOException {
        Request request = new Request.Builder()
                .addHeader("Authorization", token)
                .url(url)
                .delete()
                .build();
        Response response = client.newCall(request).execute();
        ObjectMapper mapper = new ObjectMapper();
        Map<?, ?> map = mapper.readValue(response.body().string(), Map.class);
        return map;
    }

    /**
     * upload file to service
     * @param url
     * @param token
     * @param binary
     * @return
     * @throws IOException
     */
    public static Map<?, ?> uploadBinary(String url, String token, File binary) throws IOException {
        RequestBody body = RequestBody.create(binary, MediaType.parse("application/octet-stream"));
        Request request = new Request.Builder()
                .addHeader("Authorization", token)
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        ObjectMapper mapper = new ObjectMapper();
        Map<?, ?> map = mapper.readValue(response.body().string(), Map.class);
        return map;
    }
}
