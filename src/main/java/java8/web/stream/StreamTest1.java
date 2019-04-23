package java8.web.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/***
 * 5.2生成流
 在Java 8中,集合接口有两个方法来生成流：

 stream() −为集合创建串行流。

 parallelStream() − 为集合创建并行流。
 */
public class StreamTest1 {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        filtered.forEach(System.out::println);
    }
}
