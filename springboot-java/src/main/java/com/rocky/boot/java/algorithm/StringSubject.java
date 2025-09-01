package com.rocky.boot.java.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * 字符串相关
 *
 * @author : rocky
 * @date : created in 2024/8/27 15:23
 */
public class StringSubject {

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的最长连续子字符串的长度。
     * 使用滑动窗口实现
     *
     * @param s 给定的字符串
     * @return int
     */
    public static int getMaxLengthContinuousSubstring(String s) {
        int maxLength = 1;
        int length = s.length();

        if (length <= 1) {
            return length;
        }

        // 左右指针
        int left = 0;
        int right = 0;
        Set<Character> windows = new HashSet<>();
        while (right < length) {
            char rightChar = s.charAt(right);
            while (windows.contains(rightChar)) {
                windows.remove(s.charAt(left));
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
            windows.add(rightChar);
            right++;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(StringSubject.getMaxLengthContinuousSubstring("abb"));
    }
}
