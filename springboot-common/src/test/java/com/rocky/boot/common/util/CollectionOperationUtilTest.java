package com.rocky.boot.common.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rocky
 * Description:
 * Created in 2021/3/12
 */
public class CollectionOperationUtilTest {

    @Test
    public void removeDuplicateWithOrder() {
        List<String> list = new ArrayList<>();
        list.add("beijing");
        list.add("beijing");
        list.add("shenyang");
        list.add("shenyang");
        list.add("wuhan");
        System.out.println(list.toString());
        CollectionOperationUtil.removeDuplicateWithOrder(list);
        System.out.println(list.toString());
    }
}