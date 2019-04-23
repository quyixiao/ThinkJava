package com.java8.comparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

public class ComparatorTest4_comparingInt_String_reversed {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("nihao", "hello", "world", "welcome");
        //降序，方法引用的lambda表达式的方式来实现
        Collections.sort(list, Comparator.comparingInt((ToIntFunction<String>) item -> item.length()).reversed());
        System.out.println(list);

    }
}
