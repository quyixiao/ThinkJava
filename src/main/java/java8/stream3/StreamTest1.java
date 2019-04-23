package java8.stream3;

import java.util.Arrays;
import java.util.List;


/***
 * 共享使用
 * 是否是总结起来，默认的方法，分割
 */
public class StreamTest1 {

    public static final int CONCURRENT = 0x00001000;

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello world");
        list.stream().forEach(System.out::println);
        System.out.println(CONCURRENT);

    }
}
