package java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/****
 * hello
 * welcome
 * world
 *
 * 实现单词去重复
 *
 *
 * Stream 操作类型
 * Intermediate:一个流可以后面跟随零个或多个intermediate操作，其目的的主要是打开流，做出某种程度
 *              的数据映射/过滤，然后返回一个新的流，交给下一个操作使用，这类操作都是延迟（lazy）,就是说
 *              ，仅仅调用到这类方法，并没有真正开始交流遍历
 *
 * Terminal: 一个流只能有一个terminal操作，当这个操作执行后，流就被用光了，无法再被操作，所有这必定是最后的一个操作，Terminal
 *           操作的执行，才会真正的开始流的遍历，并且会生成一个结果
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class StreamTest14_flatMap_distinct {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello welcome", "world hello", "hello world hello", "hello welcome");
        //List<String[]> result = list.stream().map(item -> item.split(" ")).distinct().collect(Collectors.toList());

        //result.forEach(item -> Arrays.asList(item).forEach(System.out::println));

        list.stream().map(item -> item.split(" ")).flatMap(Arrays::stream).distinct()
                .collect(Collectors.toList()).forEach(System.out::println);

    }

}
