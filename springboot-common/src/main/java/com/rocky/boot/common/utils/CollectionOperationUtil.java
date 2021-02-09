package com.rocky.boot.common.utils;

import java.util.*;

/**
 * @author rocky
 * Description: 集合操作工具
 * Created in 2021/2/8
 */
public class CollectionOperationUtil {

    /**
     * 有序集合去重
     *
     * @param list 列表
     */
    private static void removeDuplicateWithOrder(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (set.add(element)) {
                newList.add(element);
            }
        }
        list.clear();
        list.addAll(newList);
    }

    public static void main(String[] args) {
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
