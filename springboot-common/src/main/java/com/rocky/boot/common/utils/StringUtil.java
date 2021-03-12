package com.rocky.boot.common.utils;

import com.google.common.base.CaseFormat;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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

    /**
     * 统计单词的出现次数
     *
     * @param text
     * @return
     */
    public static String countOccurrenceOfWords(String text) {
        TreeMap<String, Integer> treeMap = new TreeMap<>();

        String[] words = text.split("[ \n\t\r.,;:!?(){}]");
        for (String word : words) {
            String key = word.toLowerCase();
            if (key.length() > 0) {
                if (treeMap.get(key) == null) {
                    treeMap.put(key, 1);
                } else {
                    int value = treeMap.get(key).intValue();
                    value++;
                    treeMap.put(key, value);
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        Set<Map.Entry<String, Integer>> entrySet = treeMap.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet) {
            builder.append(entry.getKey()).append("\t").append(entry.getValue()).append("\n");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String origin1 = "bookName";
        System.out.println(StringUtil.hump2Underline(origin1));
        String origin2 = "book_name";
        System.out.println(StringUtil.underline2Hump(origin2));

        String text = "Good morning.Have a good class.Have a good visit.Have fun!";
        System.out.println(StringUtil.countOccurrenceOfWords(text));
    }
}
