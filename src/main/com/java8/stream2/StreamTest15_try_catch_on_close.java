package com.java8.stream2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 只有第一个异常将抛出异常，其他的异常将是受到压制的异常,
 *
 * 除非是同一个异常，就不可能有受压制的异常，如果是同一种异常
 *
 */
public class StreamTest15_try_catch_on_close {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello world");


        NullPointerException nullPointerException = new NullPointerException();

        try (Stream<String> stream = list.stream()) {
            stream.onClose(() -> {
                System.out.println("aaa");
                //throw new NullPointerException("first aaa is exception null ");
                throw nullPointerException;
            }).onClose(() -> {
                System.out.println("bbb");
                //throw new ArithmeticException("arithmetic exception ");
                throw nullPointerException;
            }).forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
