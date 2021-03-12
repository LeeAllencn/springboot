package com.rocky.boot.common.base;

import java.util.*;

/**
 * @author : Rocky
 * Description: 集合的基础操作
 * Created in
 */
public class CollectionBaseOperation {

    public static void main(String[] args) {
        System.out.println("*** 1.HashMap类型:不重复元素，无序 ***");
        Map<String, Object> map = new HashMap<>(16);
        map.put("Bob", 17);
        map.put("Marry", 18);
        map.put("Jack", 19);
        map.put("Martin", 20);

        // 键值对entrySet()遍历
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.print(key + ":" + value + " ");
        }

        System.out.println();

        // 键keySet()遍历
        for (String key : map.keySet()) {
            Object value = map.get(key);
            System.out.print(key + ":" + value + " ");
        }

        System.out.println();

        System.out.println("*** 2.规则集Set ***");
        // 散列集HashSet:不重复元素，不保持插入时的顺序
        Set<String> hashSet = new HashSet<>();
        traverseSet(hashSet);

        // 链式散列集LinkedHashSet:不重复元素，保持插入时的顺序
        Set<String> linkedHashSet = new LinkedHashSet<>();
        traverseSet(linkedHashSet);

        System.out.println("*** 3.线性表：ArrayList，LinkedList：重复元素，有序 ***");
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        System.out.println(arrayList);

        System.out.println("*** 4.静态方法Arrays.asList()的使用 ***");
        List<String> list = Arrays.asList("zhangsan", "lisi", "wangwu", "maliu");
        System.out.println(list);

        System.out.println("*** 5.Collections的使用 ***");
        // 正序
        Collections.sort(list);
        System.out.println(list);
        // 倒叙
        Collections.reverse(list);
        System.out.println(list);

    }

    private static void traverseSet(Set<String> set) {
        set.add("和平");
        set.add("龙湾");
        set.add("潜江");
        set.add("武汉");
        set.add("沈阳");
        set.add("北京");
        for (String value : set) {
            System.out.print(value + " ");
        }

        System.out.println();
    }
}
