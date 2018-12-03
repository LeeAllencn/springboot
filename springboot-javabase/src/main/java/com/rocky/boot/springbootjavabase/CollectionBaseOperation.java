package com.rocky.boot.springbootjavabase;

import java.util.*;

/**
 * @Author: Rocky
 * @Description: 集合的基础操作
 * @Date: Created in 12:02 2018/6/23
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
        Iterator<Map.Entry<String, Object>> iteratorEntry = map.entrySet().iterator();
        while (iteratorEntry.hasNext()) {
            Map.Entry<String, Object> entry = iteratorEntry.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.print(key + ":" + value + " ");
        }

        System.out.println();

        // 键keySet()遍历
        Iterator<String> iteratorKey = map.keySet().iterator();
        while (iteratorKey.hasNext()) {
            String key = iteratorKey.next();
            Object value = map.get(key);
            System.out.print(key + ":" + value + " ");
        }

        System.out.println();

        System.out.println("*** 2.规则集Set ***");
        // 散列集HashSet:不重复元素，不保持插入时的顺序
        Set<String> hashSet = new HashSet<>();
        hashSet.add("和平");
        hashSet.add("龙湾");
        hashSet.add("潜江");
        hashSet.add("武汉");
        hashSet.add("沈阳");
        hashSet.add("北京");
        Iterator<String> iteratorHashSet = hashSet.iterator();
        while (iteratorHashSet.hasNext()) {
            System.out.print(iteratorHashSet.next() + " ");
        }

        System.out.println();

        // 链式散列集LinkedHashSet:不重复元素，保持插入时的顺序
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("和平");
        linkedHashSet.add("龙湾");
        linkedHashSet.add("潜江");
        linkedHashSet.add("武汉");
        linkedHashSet.add("沈阳");
        linkedHashSet.add("北京");
        Iterator<String> iteratorLinkedHashSet = linkedHashSet.iterator();
        while (iteratorLinkedHashSet.hasNext()) {
            System.out.print(iteratorLinkedHashSet.next() + " ");
        }

        System.out.println();

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
        List<String> list = Arrays.asList("zhangsan","lisi","wangwu","maliu");
        System.out.println(list);

        System.out.println("*** 5.Collections的使用 ***");
        // 正序
        Collections.sort(list);
        System.out.println(list);
        // 倒叙
        Collections.reverse(list);
        System.out.println(list);

    }
}
