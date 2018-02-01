package com.rocky.boot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Rocky on 2017-10-31.
 */
public class CoderUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoderUtil.class);

    /**
     * 将URL编码
     */
    public static String encodeURL(String source) {
        String target;
        try {
            target = URLEncoder.encode(source, "UTF-8");
        } catch (Exception e) {
            LOGGER.error("encode url failure", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将URL解码
     */
    public static String decodeURL(String source) {
        String target;
        try {
            target = URLDecoder.decode(source, "UTF-8");
        } catch (Exception e) {
            LOGGER.error("decode url failure", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    public static String encodeBASE64(byte[] key) {
        if (key == null)
            return null;
        return (new BASE64Encoder().encode(key));
    }

    // BASE64加密
    public static String encryptBASE64(String key)throws Exception{
        if (key == null)
            return null;
        return (new BASE64Encoder()).encode(key.getBytes());
    }

    //BASE64解密
    public static String decryptBASE64(String key) throws Exception{
        if (key == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(key);
        return new String(b);
    }

    public static void main(String[] args) throws Exception {
        String str = "lixin";
        String str1 = CoderUtil.encryptBASE64(str);
        String str2 = CoderUtil.decryptBASE64(str1);
        System.out.println(str1);
        System.out.println(str2);
        String str3 = CoderUtil.encodeBASE64(new byte[]{'l','e','e'});
        System.out.println(str3);
        System.out.println(CoderUtil.decryptBASE64(str3));
    }

}
