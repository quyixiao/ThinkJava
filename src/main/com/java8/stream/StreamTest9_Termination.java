package com.java8.stream;

import java.util.Arrays;
import java.util.List;

/***
 * termination
 */
public class StreamTest9_Termination {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello world");
        list.stream().map(item -> item.substring(0, 1).toUpperCase() + item.substring(1)).forEach(System.out::println);
        System.out.println("============================================================");

        //对于流的所有的方法，如果没有遇到及早求值或者中止操作的话，stream是惰性求值的，
        List<String> list1 = Arrays.asList("hello", "world", "hello world");
        list1.stream().map(item ->{
                    String result =  item.substring(0, 1).toUpperCase() + item.substring(1);
                    System.out.println("test");
                    return result;
                }
        ).forEach(System.out::println);





    }
}
