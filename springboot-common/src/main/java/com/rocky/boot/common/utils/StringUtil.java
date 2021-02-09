package com.rocky.boot.common.utils;

import com.google.common.base.CaseFormat;

/**
 * @author rocky
 * @Date: Created in 2019/3/7
 */
public class StringUtil {
    /**
     * 驼峰转下划线
     *
     * @param origin
     * @return
     */
    public static String hump2Underline(String origin) {
        String target = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, origin);
        return target;
    }

    /**
     * 下划线转驼峰
     *
     * @param origin
     * @return
     */
    public static String underline2Hump(String origin) {
        String target = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, origin);
        return target;
    }

    public static void main(String[] args) {
        String origin1 = "bookName";
        System.out.println(StringUtil.hump2Underline(origin1));
        String origin2 = "book_name";
        System.out.println(StringUtil.underline2Hump(origin2));
    }
}
