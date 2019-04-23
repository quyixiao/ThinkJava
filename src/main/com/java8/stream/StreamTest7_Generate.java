package com.java8.stream;

import java.util.UUID;
import java.util.stream.Stream;

public class StreamTest7_Generate {


    public static void main(String[] args) {
        Stream<String> stream = Stream.generate(UUID.randomUUID()::toString);
        //找到第一个元素,
        //System.out.println(stream.findFirst().get());
        // System.out.println(stream.findFirst());

        stream.findFirst().ifPresent(System.out::println);
    }



}
