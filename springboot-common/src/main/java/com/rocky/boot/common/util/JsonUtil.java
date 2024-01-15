package com.rocky.boot.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @author rocky
 * Created by Rocky on 2017-10-10.
 */
@Slf4j
public class JsonUtil {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * json转list
     * @param jsonStr json
     * @return list
     */
    public static List<?> json2List(String jsonStr) {
        return JSON.parseArray(jsonStr);
    }

    /**
     * json转list（实体）
     * @param jsonStr json
     * @param clazz clazz
     * @param <T> object
     * @return list
     */
    public static <T> List<T> json2List(String jsonStr, Class<T> clazz) {
        return JSON.parseArray(jsonStr, clazz);
    }


    /**
     * list转json
     *
     * @param list list
     * @return json
     */
    public static String list2Json(List<?> list) {
        return JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * json转map
     * @param json json
     * @return map
     */
    public static Map<?, ?> json2Map(String json) {
        return JSON.parseObject(json, Map.class);
    }

    /**
     * map转json
     *
     * @param map map
     * @return json
     */
    public static String map2Json(Map<?, ?> map) {
        return JSON.toJSONString(map, true);
    }

    /**
     * json转jsonObject
     *
     * @param json json
     * @return jsonObject
     */
    public static JSONObject json2Obj(String json) {
        return JSON.parseObject(json);
    }

    public static <T> T json2Obj(String json, Class<T> cls) {
        return JSON.parseObject(json, cls);
    }

    public static Object obj2Json(Object obj) {
        return JSON.toJSON(obj);
    }

    public static String obj2JsonStr(Object obj) {
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
            log.error("convert POJO to JSON failure", e);
            throw new RuntimeException(e);
        }
        return json;
    }

    /**
     * 将 JSON 转为 POJO
     */
    public static <T> T fromJson(String json, Class<T> type) {
        T pojo;
        try {
            pojo = OBJECT_MAPPER.readValue(json, type);
        } catch (Exception e) {
            log.error("convert JSON to POJO failure", e);
            throw new RuntimeException(e);
        }
        return pojo;
    }
}
