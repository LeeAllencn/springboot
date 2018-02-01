package com.rocky.boot.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Rocky on 2017-10-10.
 */
public class JsonUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * json转list
     * @param jsonStr
     * @return
     */
    public static List<?> json2List(String jsonStr){
        List<?> list = JSON.parseArray(jsonStr);
        return list;
    }

    /**
     * json转list（实体）
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> json2List(String jsonStr, Class<T> clazz){
        List<T> list = JSON.parseArray(jsonStr, clazz);
        return list;
    }


    /**
     * list转json
     * @param list
     * @return
     */
    public static String list2Json(List<?> list) {
        String json= JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);
        return json;
    }

    /**
     * json转map
     * @param json
     * @return
     */
    public static Map<String,String> json2Map(String json){
        Map<String,String> map = (Map<String,String>)JSON.parse(json);
        return map;
    }

    /**
     * map转json
     * @param map
     * @return
     */
    public static String map2Json(Map<?,?> map){
        String json = JSON.toJSONString(map,true);
        return json;
    }

    /**
     * json转jsonObject
     * @param json
     * @return
     */
    public static JSONObject json2Obj(String json){
        JSONObject obj = JSON.parseObject(json);
        return obj;
    }

    public static <T> T json2Obj(String json, Class<T> cls){
        return JSON.parseObject(json, cls);
    }

    public static Object obj2Json(Object obj){
        return JSON.toJSON(obj);
    }

    public static String obj2JsonStr(Object obj){
        return JSON.toJSONString(obj);
    }

    /**
     * 将 POJO 转为 JSON
     */
    public static <T> String toJson(T obj) {
        String json;
        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            LOGGER.error("convert POJO to JSON failure", e);
            throw new RuntimeException(e);
        }
        return json;
    }

    /**
     * 将 JSON 转为 POJO
     */
    public static <T> T fromJson(String json,Class<T> type) {
        T pojo;
        try {
            pojo = OBJECT_MAPPER.readValue(json, type);
        } catch (Exception e) {
            LOGGER.error("convert JSON to POJO failure", e);
            throw new RuntimeException(e);
        }
        return pojo;
    }

    public static void main(String args[]){
        List<String> list = new ArrayList<>();
        list.add("20161227101817");
        list.add("20161227102112");
        System.out.println(JsonUtil.list2Json(list));
    }
}
