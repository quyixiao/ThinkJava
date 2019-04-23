package java8.stream;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/***
 * item 2
 */
public class StreamTest15_flatMap_combine {

    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("Hi", "Hello", "你好");
        List<String> list2 = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu");

        List<String> result = list1.stream().flatMap(item -> list2.stream().map(item2 -> item + " " + item2))
                .collect(Collectors.toList());

        result.forEach(System.out::println);
    }
}
