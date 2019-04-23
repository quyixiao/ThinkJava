package com.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 流的出现
 *
 *  流由3部分构成
 *      1.源
 *      2.零个或者多个跟单操作
 *      3.终止操作
 *   1.惰性求值
 *   2.及早求值
 *
 *
 */
public class StreamTest {

    public static void main(String[] args) {
        //Stream 使用方法
        Stream stream = Stream.of("hello","world","hello world");
        String [] myArray = new String[]{"hello","world","hello world"};
        Stream stream1 = Stream.of(myArray);
        Stream stream2 = Arrays.stream(myArray);
        List<String> list = Arrays.asList(myArray);
        Stream stream3 = list.stream();



    }
}



























