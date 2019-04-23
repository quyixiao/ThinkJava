package com.java8.comparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ComparatorTest1_collections_sort {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("nihao", "hello", "world", "welcome");
        //升序
        //Collections.sort(list, (item1, item2) -> item2.length() - item1.length());
        //降序
        Collections.sort(list, (item1, item2) -> item2.length() - item1.length());

        System.out.println(list);

    }
}
