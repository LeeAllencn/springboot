package com.rocky.boot.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Created by Rocky on 2017-10-31.
 */
public class CoderUtil {

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
