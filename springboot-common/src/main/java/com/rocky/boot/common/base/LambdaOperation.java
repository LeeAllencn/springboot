package com.rocky.boot.common.base;

import cn.hutool.json.JSONUtil;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author rocky
 * Description: Lambda 表达式
 * 语法:
 * (parameters) -> expression
 * (parameters) ->{ statements; }
 * <p>
 * Created in 2021/3/17
 */
public class LambdaOperation {

    public static void main(String[] args) {

        // example1 使用lambda表达式对列表进行迭代
        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(System.out::println);

        // example2 使用lambda表达式和函数式接口Predicate
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        filter(languages, (str) -> str.startsWith("J"));

        System.out.println("Languages which ends with a ");
        filter(languages, (str) -> str.endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str) -> true);

        System.out.println("Print no language : ");
        filter(languages, (str) -> false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str) -> str.length() > 4);

        // example3 使用lambda表达式的Map和Reduce示例
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        costBeforeTax.stream().map((cost) -> cost + .12 * cost).forEach(System.out::println);
        Optional<Double> total = costBeforeTax.stream().map((cost) -> cost + .12 * cost).reduce(Double::sum);
        total.ifPresent(aDouble -> System.out.println("Total : " + aDouble));

        // example4 通过过滤创建一个String列表
        List<String> strList = Arrays.asList("abc", "", "bcd", "", "defg", "jk");
        List<String> filtered = strList.stream().filter(x -> x.length() > 2).collect(Collectors.toList());
        System.out.printf("Original List : %s, filtered list : %s %n", strList, filtered);

        // example5 对列表的每个元素应用函数
        List<String> g7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.", "Canada");
        String g7Countries = g7.stream().map(String::toUpperCase).collect(Collectors.joining(", "));
        System.out.println(g7Countries);

        // example6 复制不同的值，创建一个子列表
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> distinct = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.printf("Original List : %s,  Square Without duplicates : %s %n", numbers, distinct);

        // example7  计算集合元素的最大值、最小值、总和以及平均值
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics intSummaryStatistics = primes.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + intSummaryStatistics.getMax());
        System.out.println("Lowest prime number in List : " + intSummaryStatistics.getMin());
        System.out.println("Sum of all prime numbers : " + intSummaryStatistics.getSum());
        System.out.println("Average of all prime numbers : " + intSummaryStatistics.getAverage());

        // example8 分组
        User user1 = new User("Jack", 19);
        User user2 = new User("Tom", 19);
        User user3 = new User("Marry", 21);
        User user4 = new User("Joe", 19);
        User user5 = new User("Rocky", 21);
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        Map<Integer, List<User>> listMap = userList.stream().collect(Collectors.groupingBy(User::getAge));
        Map<Integer, Long> longMap = userList.stream().collect(Collectors.groupingBy(User::getAge, Collectors.counting()));
        System.out.println(JSONUtil.toJsonStr(listMap));
        System.out.println(JSONUtil.toJsonStr(longMap));
    }

    private static void filter(List<String> languages, Predicate<String> condition) {
        languages.stream().filter(condition).forEach(System.out::println);
    }
}
