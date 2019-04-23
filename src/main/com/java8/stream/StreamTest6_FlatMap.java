package com.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest6_FlatMap {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello world");
        //将集合中的每个元素转化为大写，然后输出
        list.forEach(item -> System.out.println(item.toUpperCase()));
        //转化为
        List<String> a = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        a.forEach(System.out::println);
        //
        System.out.println("=========================================");
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5, 6);
        list2.stream().map(item -> item * item).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
        //将集合转化合并为一个stream 转化为合并为一个集合，然后再 平方
        Stream<List<Integer>> stream = Stream.of(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
        stream.flatMap(theList -> theList.stream()).map(item -> item * item).forEach(System.out::println);


    }


}
