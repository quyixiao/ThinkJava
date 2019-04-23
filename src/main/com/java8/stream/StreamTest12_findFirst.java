package com.java8.stream;

import java.util.Arrays;
import java.util.List;

public class StreamTest12_findFirst {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("hello1", "world", "hello world");
        list.stream().filter(item -> item.length() == 5).limit(1).forEach(System.out::println);
        System.out.println("===============================================");
        list.parallelStream().filter(item -> item.length() == 5).limit(1).forEach(System.out::println);
        System.out.println("===============================================");
        list.parallelStream().filter(item -> item.length() == 5).findFirst().ifPresent(System.out::println);

    }
}
