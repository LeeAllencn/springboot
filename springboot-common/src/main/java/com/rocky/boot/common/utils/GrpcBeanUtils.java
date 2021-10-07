package com.rocky.boot.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;

import java.io.IOException;

/**
 * @author rocky
 * Description: Grpc对象和java bean的复制
 * Created in 2021/3/17
 */
public class GrpcBeanUtils {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将java bean对象拷贝到grpc对象
     *
     * @param sourcePojoBean java bean对象
     * @param destBuilder    grpc对象
     * @throws IOException IOException
     */
    public static void toProtoBean(Object sourcePojoBean, Message.Builder destBuilder) throws IOException {
        if (sourcePojoBean == null) {
            throw new IllegalArgumentException("No source pojo specified");
        }
        if (destBuilder == null) {
            throw new IllegalArgumentException("No destination message builder specified");
        }

        String json = new GsonBuilder().setDateFormat(DATE_TIME_FORMAT).create().toJson(sourcePojoBean);
        JsonFormat.parser().ignoringUnknownFields().merge(json, destBuilder);
    }

    /**
     * 将grpc对象拷贝到java bean对象
     *
     * @param sourceMessage grpc对象
     * @param destPojoClass java bean对象
     * @param <T>           clazz
     * @return T
     * @throws IOException IOException
     */
    public static <T> T toPojoBean(Message sourceMessage, Class<T> destPojoClass) throws IOException {
        if (sourceMessage == null) {
            throw new IllegalArgumentException("No source message specified");
        }
        if (destPojoClass == null) {
            throw new IllegalArgumentException("No destination pojo class specified");
        }
        String json = JsonFormat.printer().includingDefaultValueFields().print(sourceMessage);
        return new Gson().fromJson(json, destPojoClass);
    }
}
