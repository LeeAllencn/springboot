package com.rocky.boot.common.util;

import com.google.common.base.CaseFormat;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author rocky
 * Date: Created in 2019/3/7
 */
public class StringUtil {

    /**
     * 驼峰转下划线
     * @param origin 驼峰字符串
     * @return 下划线字符串
     */
    public static String hump2Underline(String origin) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, origin);
    }

    /**
     * 下划线转驼峰
     * @param origin 下划线字符串
     * @return 驼峰字符串
     */
    public static String underline2Hump(String origin) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, origin);
    }

    /**
     * 统计单词的出现次数
     *
     * @param text 字符串文本
     * @return 单词统计结果
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
                    int value = treeMap.get(key);
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
}
