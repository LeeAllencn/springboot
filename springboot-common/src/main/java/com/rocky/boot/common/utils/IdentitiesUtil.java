package com.rocky.boot.common.utils;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * 封装各种生成唯一性ID算法的工具类.
 * <p>
 * @author rocky
 * Created by Rocky on 2017-10-25.
 */
public class IdentitiesUtil {

    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间有-分割.
     */
    public static String uuidWithSplitLine() {
        return UUID.randomUUID().toString();
    }

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuidWithOutSplitLine() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 使用SecureRandom随机生成Long.
     */
    public static long randomLong() {
        return Math.abs(RANDOM.nextLong());
    }
}
