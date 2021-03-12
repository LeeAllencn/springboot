package com.rocky.boot.common.utils;

import org.junit.Test;

/**
 * @author rocky
 * Description:
 * Created in 2021/3/12
 */
public class StringUtilTest {

    @Test
    public void hump2Underline() {
        System.out.println(StringUtil.hump2Underline("bookName"));
    }

    @Test
    public void underline2Hump() {
        System.out.println(StringUtil.underline2Hump("book_name"));
    }

    @Test
    public void countOccurrenceOfWords() {
        String text = "Good morning.Have a good class.Have a good visit.Have fun!";
        System.out.println(StringUtil.countOccurrenceOfWords(text));
    }
}