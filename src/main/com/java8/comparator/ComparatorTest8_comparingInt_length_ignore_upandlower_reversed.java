package com.java8.comparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/****
 * 新改变的实现
 * 只对字符串
 */
public class ComparatorTest8_comparingInt_length_ignore_upandlower_reversed {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("nihao", "hello", "world", "welcome");
        //先进行第一个比较器的比较，然后，再进行第二次比较器的比较
        Collections.sort(list, Comparator.comparingInt(String::length).thenComparing((item1,item2)->
                item1.toUpperCase().compareToIgnoreCase(item2.toUpperCase())).reversed());
        System.out.println(list);

    }
}
