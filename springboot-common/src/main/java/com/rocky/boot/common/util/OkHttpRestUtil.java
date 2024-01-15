package com.rocky.boot.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author rocky
 * Created by Rocky on 2017-10-10.
 */
public class OkHttpRestUtil {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final OkHttpClient CLIENT = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES).writeTimeout(5, TimeUnit.MINUTES).build();

    /**
     * get request
     *
     * @param url url
     * @return String
     * @throws IOException 异常
     */
    public static String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = CLIENT.newCall(request).execute();
        return Objects.requireNonNull(response.body()).string();
    }

    public static Map<?, ?> get(String url, String token) throws IOException {
        Request request = new Request.Builder()
                .addHeader("Authorization", token)
                .url(url)
                .build();
        Response response = CLIENT.newCall(request).execute();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(Objects.requireNonNull(response.body()).string(), Map.class);
    }

    /**
     * post request
     * @param url url
     * @param token token
     * @param obj obj
     * @return Map
     * @throws IOException 异常
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
        System.out.println("------------------" + CLIENT.connectTimeoutMillis() / 1000 + "--------------------------" + CLIENT.readTimeoutMillis() / 1000);
        Response response = CLIENT.newCall(request).execute();
        return mapper.readValue(Objects.requireNonNull(response.body()).string(), Map.class);
    }

    public static Map<?, ?> post(String url, String token, String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + token)
                .url(url)
                .post(body)
                .build();
        Response response = CLIENT.newCall(request).execute();
        return mapper.readValue(Objects.requireNonNull(response.body()).string(), Map.class);
    }

    /**
     * put request
     * @param url url
     * @param token token
     * @param obj obj
     * @return Map
     * @throws IOException 异常
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
        Response response = CLIENT.newCall(request).execute();
        String resBody = Objects.requireNonNull(response.body()).string();
        Map<?, ?> map = null;
        if (StringUtils.isNotEmpty(resBody)) {
            map = mapper.readValue(resBody, Map.class);
        }
        return map;
    }

    /**
     * delete request
     * @param url url
     * @param token token
     * @return Map
     * @throws IOException 异常
     */
    public static Map<?, ?> delete(String url, String token) throws IOException {
        Request request = new Request.Builder()
                .addHeader("Authorization", token)
                .url(url)
                .delete()
                .build();
        Response response = CLIENT.newCall(request).execute();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(Objects.requireNonNull(response.body()).string(), Map.class);
    }

    /**
     * upload file to service
     * @param url url
     * @param token token
     * @param binary 二进制
     * @return Map
     * @throws IOException 异常
     */
    public static Map<?, ?> uploadBinary(String url, String token, File binary) throws IOException {
        RequestBody body = RequestBody.create(binary, MediaType.parse("application/octet-stream"));
        Request request = new Request.Builder()
                .addHeader("Authorization", token)
                .url(url)
                .post(body)
                .build();
        Response response = CLIENT.newCall(request).execute();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(Objects.requireNonNull(response.body()).string(), Map.class);
    }
}
