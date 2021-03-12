package com.rocky.boot.common.utils;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 编码工具类
 * @author rocky
 */
@Slf4j
public class CoderUtil {

    /**
     * 将URL编码
     */
    public static String encodeUrl(String source) {
        String target;
        try {
            target = URLEncoder.encode(source, "UTF-8");
        } catch (Exception e) {
            log.error("encode url failure", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将URL解码
     */
    public static String decodeUrl(String source) {
        String target;
        try {
            target = URLDecoder.decode(source, "UTF-8");
        } catch (Exception e) {
            log.error("decode url failure", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * BASE64加密
     *
     * @param key byte[]
     * @return String
     */
    public static String encodeBase64(byte[] key) {
        if (key == null) {
            return null;
        }
        return (new BASE64Encoder().encode(key));
    }

    /**
     * BASE64加密
     *
     * @param key String
     * @return String
     */
    public static String encryptBase64(String key) {
        if (key == null) {
            return null;
        }
        return (new BASE64Encoder()).encode(key.getBytes());
    }

    /**
     * BASE64解密
     *
     * @param key String
     * @return String
     * @throws Exception 异常
     */
    public static String decryptBase64(String key) throws Exception {
        if (key == null) {
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(key);
        return new String(b);
    }
}
