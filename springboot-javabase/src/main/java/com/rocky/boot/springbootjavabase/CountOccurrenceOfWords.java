package com.rocky.boot.springbootjavabase;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @Author: Rocky
 * @Description: 统计单词的出现次数
 * @Date: Created in 13:03 2018/6/23
 */
public class CountOccurrenceOfWords {

    public static void main(String[] args) {
        String text = "Good morning.Have a good class.Have a good visit.Have fun!";

        TreeMap<String, Integer> treeMap = new TreeMap<>();

        String[] words = text.split("[ \n\t\r.,;:!?(){}]");
        for(int i = 0;i < words.length;i ++){
            String key = words[i].toLowerCase();
            if(key.length() > 0){
                if(treeMap.get(key) == null){
                    treeMap.put(key, 1);
                }else{
                    int value = treeMap.get(key).intValue();
                    value++;
                    treeMap.put(key, value);
                }
            }
        }

        Set<Map.Entry<String, Integer>> entrySet = treeMap.entrySet();
        for(Map.Entry<String, Integer> entry : entrySet){
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
    }
}
