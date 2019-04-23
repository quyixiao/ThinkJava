package com.java8.stream;

import java.util.Arrays;
import java.util.List;

/***
 *  打印字符串的长度
 *      流是存在短路运算的
 *
 *
 *
 */
public class StreamTest13_termination {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello1", "world", "hello world");
        list.stream().mapToInt(item -> {
            int lenth = item.length();
            System.out.println(item);
            return lenth;
        }).forEach(System.out::println);
    }

}
