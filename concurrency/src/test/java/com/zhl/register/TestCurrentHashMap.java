package com.zhl.register;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program codeX
 * @description:测试CurrentHashMap
 * @author: meilong
 * @create: 2019/08/26 18:55
 */
public class TestCurrentHashMap {

    private static final ConcurrentHashMap<String, List<String>> CACHE = new ConcurrentHashMap<>();

    private static List<String> getList(String name) {
        List<String> list = CACHE.get(name);
        if (list == null) {
            list = new ArrayList<>();
            list.add(name + " a");
            list.add(name + " b");
            // note:putIfAbsent的返回值的非空判断及处理
            List<String> v = CACHE.putIfAbsent(name, list);
            if (v != null) {
                list = v;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> key1List = getList("key1");
        List<String> key2List = getList("key2");

        System.out.println(key1List);
        System.out.println(key2List);
    }
}
