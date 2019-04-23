package java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest4 {

    public static void main(String[] args) {
        Stream<String> stream = Stream.of("hello", "world", "hello world");
        //String [] stringArrays = stream.toArray(length -> new String[length]);
        // Arrays.asList(stringArrays).forEach(System.out::println);

        String[] stringArrays1 = stream.toArray(String[]::new);
        Arrays.asList(stringArrays1).forEach(System.out::println);

        Stream<String> stream1 = Stream.of("hello", "world", "helloworld");
        //将流转化为List
        List<String> list = stream1.collect(Collectors.toList());
        list.forEach(System.out::println);

        Stream<String> stream2 = Stream.of("hello", "world", "helloworld");
        //
        List<String> list2 = stream2.collect(() -> new ArrayList(), (theList, item) -> theList.add(item),
                (theList1, theList2) -> theList1.addAll(theList2));

        Stream<String> stream3 = Stream.of("hello", "world", "helloworld");
        List<String> list3 = stream3.collect(LinkedList::new, LinkedList::add, LinkedList::addAll);


        list3.forEach(System.out::println);

        Stream<String> stream4 = Stream.of("hello", "world", "helloworld");
        List<String> asList = stream4.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        asList.forEach(System.out::println);


        Stream<String> stringStream = Stream.of("hello", "world", "helloworld");
        // 第三个参数  function for combining two values, which must be compatible with the accumulator function
        // 将中间生成的 List返回给外部，从语意上是很好理解的
        String concat = stringStream.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
        System.out.println(concat);

        //


    }
}
