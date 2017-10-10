package com.rocky.boot.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Rocky on 2017-10-10.
 */
public class OKHttpRestUtil {
    private static OkHttpClient client = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES).writeTimeout(5, TimeUnit.MINUTES).build();

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    //get request
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

    //post request
    public static Map<?, ?> post(String url,String token,Object obj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(obj);
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), json);
        Request request = new Request.Builder()
                .addHeader("Authorization", token)
                .url(url)
                .post(body)
                .build();
        System.out.println("------------------"+client.connectTimeoutMillis()/1000+ "--------------------------"+client.readTimeoutMillis()/1000);
        Response response = client.newCall(request).execute();
        Map<?, ?> map = mapper.readValue(response.body().string(), Map.class);
        return map;
    }

    public static Map<?, ?> post(String url,String token, String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + token)
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        Map<?, ?> resultMap = mapper.readValue(response.body().string(), Map.class);
        return resultMap;
    }

    //put request
    public static Map<?, ?> put(String url, String token, Object obj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(obj);
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), content);
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

    //delete request
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

    //upload file to service
    public static Map<?, ?> uploadBinary(String url, String token, File binary) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("application/octet-stream"), binary);
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
