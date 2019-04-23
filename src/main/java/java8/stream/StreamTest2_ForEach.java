package java8.stream;

import java.util.stream.IntStream;

public class StreamTest2_ForEach {

    public static void main(String[] args) {
        IntStream.of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}).forEach(System.out::println);
        System.out.println("--------------------------------");
        //构造一个3开头，8结尾的，不包含8
        IntStream.range(3, 8).forEach(System.out::println);
        System.out.println("=====================================");
        IntStream.rangeClosed(3, 8).forEach(System.out::println);


    }
}
