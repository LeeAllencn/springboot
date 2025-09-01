package com.rocky.boot.java.algorithm.doublepointer;

/**
 * 验证回文数
 * @author : rocky
 * @date : created in 2025/1/12 11:45
 */
public class Palindrome {

    public static boolean isPalindrome(String s) {
        StringBuilder newStr = new StringBuilder();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                newStr.append(Character.toLowerCase(ch));
            }
        }
        int n = newStr.length();
        int left = 0, right = n - 1;
        while (left < right) {
            if (Character.toLowerCase(newStr.charAt(left)) != Character.toLowerCase(newStr.charAt(right))) {
                return false;
            }
            ++left;
            --right;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s));
    }
}
