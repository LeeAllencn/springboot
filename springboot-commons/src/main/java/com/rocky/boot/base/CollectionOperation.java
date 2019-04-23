package com.rocky.boot.base;

import java.util.*;

/**
 * @author rocky
 * Description:
 * Created in 2019/4/23
 */
public class CollectionOperation {

    /**
     * 有序集合去重
     * @param list
     */
    private static void removeDuplicateWithOrder(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
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
        CollectionOperation.removeDuplicateWithOrder(list);
        System.out.println(list.toString());
    }
}
