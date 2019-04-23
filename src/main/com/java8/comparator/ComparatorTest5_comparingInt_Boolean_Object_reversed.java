package com.java8.comparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/****
 * 新改变的实现
 */
public class ComparatorTest5_comparingInt_Boolean_Object_reversed {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("nihao", "hello", "world", "welcome");
        //降序，方法引用的lambda表达式的方式来实现
        // Collections.sort(list, Comparator.comparingInt((Boolean item) ->1).reversed());
        Collections.sort(list, Comparator.comparingInt((Object item) -> 1).reversed());
        Collections.sort(list, Comparator.comparingInt(item -> item.length()));
        System.out.println(list);

    }
}
