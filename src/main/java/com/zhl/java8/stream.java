package com.zhl.java8;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/07/30 07:28
 */
public class stream {
    public static void main(String[] args) {

        User user1 = new User("张三", 11L);
        User user2 = new User("李四", 12L);
        User user3 = new User("王五", 11L);

        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);

        testList();
        testListToMap(list);
        testGroupBy(list);
        testFilter(list);
        testSum(list);
    }

    public static void testSum(List<User> list) {
        Long sum = list.stream().mapToLong(User::getAge).sum();
        System.out.println(sum);
    }

    public static void testFilter(List<User> list) {
        List<User> li = list.stream().filter(a -> a.getAge().equals(11L)).collect(Collectors.toList());
        System.out.println(li);
    }

    public static void testGroupBy(List<User> list) {
        Map<Long, List<User>> map = list.stream().collect(Collectors.groupingBy(a -> a.getAge()));
        System.out.println(map);
    }

    public static void testListToMap(List<User> list) {

        Map<String, User> map = list.stream().collect(Collectors.toMap(User::getName, Function.identity()));
        System.out.println(map);

        // Error
        /// Map<Long, User> map2 = list.stream().collect(Collectors.toMap(User::getAge, Function.identity()));

        // Correct
        Map<Long, User> map2 = list.stream().collect(Collectors.toMap(User::getAge, a -> a, (k1, k2) -> k1));

        // Correct
        Map<Long, User> map3 = list.stream().collect(Collectors.toMap(a -> a.getAge(), a -> a, (k1, k2) -> k1));
        System.out.println(map3);

        Map<Long, User> map4 = list.stream().collect(Collectors.toMap(a -> a.getAge(), a -> a, (k1, k2) -> k1, LinkedHashMap::new));
        System.out.println(map4);
    }

    public static void testList() {
        User user = new User("张三", 11L);
        List<User> list = new ArrayList<>();
        list.add(user);
        List<String> messages = list.stream().map(User::getName).collect(Collectors.toList());
        System.out.println(messages);
    }
}


class User {
    private String name;
    private Long age;

    public User() {

    }

    public User(String name, Long age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}