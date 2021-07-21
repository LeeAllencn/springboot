package com.rocky.boot.external.client.utils;

import cn.hutool.core.bean.BeanUtil;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Retrofit工具类
 * @author : rocky
 * @date : created in 2021/6/29
 */
public class RetrofitUtils {

    /**
     * 创建Retrofit
     * @param client OkHttpClient
     * @param baseUrl String
     * @return Retrofit
     */
    public static Retrofit createRetrofit(OkHttpClient client, String baseUrl) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * 将对象转换为Map<String, String>
     * @param object 对象
     * @return Map<String, String>
     */
    public static Map<String, String> convertStringMap(Object object) {
        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(object, false, true);
        Map<String, String> stringMap = new HashMap<>(stringObjectMap.size());
        for (Map.Entry<String, Object> entry : stringObjectMap.entrySet()) {
            stringMap.put(entry.getKey(), String.valueOf(entry.getValue()));
        }
        return stringMap;
    }
}
