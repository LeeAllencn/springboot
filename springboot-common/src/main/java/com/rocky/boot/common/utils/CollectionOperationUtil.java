package com.rocky.boot.common.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public static <E> void removeDuplicateWithOrder(List<E> list) {
        Set<E> set = new HashSet<>();
        List<E> newList = new ArrayList<>();
        for (E element : list) {
            if (set.add(element)) {
                newList.add(element);
            }
        }
        list.clear();
        list.addAll(newList);
    }
}
