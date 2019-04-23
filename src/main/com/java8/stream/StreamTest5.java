package com.java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest5 {

    public static void main(String[] args) {
        //转化为List
        Stream<String> stream = Stream.of("hello", "world", "hello world");
        List<String> list = stream.collect(Collectors.toCollection(ArrayList::new));
        list.forEach(System.out::println);


        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        // 转化为treeSet
        Stream<String> strea1 = Stream.of("hello", "world", "hello world");
        Set<String> list1 = strea1.collect(Collectors.toCollection(TreeSet::new));
        list1.forEach(System.out::println);

        System.out.println("============================================");


        // 转化为treeSet
        Stream<String> strea2 = Stream.of("hello", "world", "hello world");
        String list2 = strea2.collect(Collectors.joining());
        System.out.println(list2);

    }
}
