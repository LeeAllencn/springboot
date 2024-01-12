package com.rocky.boot.common.utils;

import org.junit.Test;

/**
 * @author rocky
 * Description:
 * Created in 2021/3/12
 */
public class CoderUtilTest {

    private final String str = "rocky";

    @Test
    public void encodeUrl() {
        System.out.println(CoderUtil.encodeUrl("http://www.baidu.com?search=rocky"));
    }

    @Test
    public void decodeUrl() {
        System.out.println(CoderUtil.decodeUrl(CoderUtil.encodeUrl("http://www.baidu.com?search=rocky")));
    }

    @Test
    public void encodeBase64() {
        System.out.println(CoderUtil.encodeBase64(new byte[]{'l', 'e', 'e'}));
    }

    @Test
    public void encryptBase64() {
        System.out.println(CoderUtil.encryptBase64(str));
    }

    @Test
    public void decryptBase64() throws Exception {
        System.out.println(CoderUtil.decryptBase64(CoderUtil.encryptBase64(str)));
        System.out.println(CoderUtil.decryptBase64(CoderUtil.encodeBase64(new byte[]{'l', 'e', 'e'})));

    }
}