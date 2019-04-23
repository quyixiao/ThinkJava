package java8.stream;

import java.util.IntSummaryStatistics;
import java.util.OptionalInt;
import java.util.stream.Stream;

public class StreamTest8_Iterate {

    public static void main(String[] args) {
        //一定要用limit来做限制，不然会变成一个无限流
        Stream.iterate(1, item -> item + 2).limit(10).forEach(System.out::println);
        //找出该流中的大于2的元素，然后将每个元素乘以2，然后忽略掉流中的前两个元素，然后再取出流中的前两个元素，最后求出流中的元素的总和
        System.out.println("============================sum=============================");
        Stream<Integer> stream = Stream.iterate(1, item -> item + 2).limit(10);
        // 忽略掉前两个元素是skip方法，只取前两个元素是limit,sum是总元素
        int sum = stream.filter(item -> item > 2).mapToInt(item -> item * 2).skip(2).limit(2).sum();
        System.out.println(sum);
        System.out.println("=============================min============================");
        Stream<Integer> stream1 = Stream.iterate(1, item -> item + 2).limit(10);
        OptionalInt min = stream1.filter(item -> item > 2).mapToInt(item -> item * 2).skip(2).limit(2).min();
        System.out.println(min.getAsInt());
        System.out.println("==============================max===========================");
        Stream<Integer> stream2 = Stream.iterate(1, item -> item + 2).limit(10);
        OptionalInt max = stream2.filter(item -> item > 2).mapToInt(item -> item * 2).skip(2).limit(2).max();
        max.ifPresent(System.out::println);
        System.out.println("==============================other===========================");
        //找出该流中的大于2的元素，然后将每个元素乘以2，然后忽略掉流中的前两个元素，然后再取出流中的前两个元素，最后求出流中的元素的最小值，最大值，总和
        Stream<Integer> stream3 = Stream.iterate(1, item -> item + 2).limit(10);
        IntSummaryStatistics minMaxSum = stream3.filter(item -> item > 10).mapToInt(item -> item * 2).skip(2).limit(2).summaryStatistics();
        System.out.println(minMaxSum.getMax());
        System.out.println(minMaxSum.getMin());
        System.out.println(minMaxSum.getAverage());
        System.out.println(minMaxSum.getCount());
        System.out.println(minMaxSum.getSum());

        System.out.println("==============================other1,filter中过滤元素，如果元素不符合条件===========================");
        //找出该流中的大于2的元素，然后将每个元素乘以2，然后忽略掉流中的前两个元素，然后再取出流中的前两个元素，最后求出流中的元素的最小值，最大值，总和
        Stream<Integer> stream4 = Stream.iterate(1, item -> item + 2).limit(10);
        IntSummaryStatistics minMaxSum4 = stream4.filter(item -> item > 200).mapToInt(item -> item * 2).skip(2).limit(2).summaryStatistics();
        System.out.println(minMaxSum4.getMax());
        System.out.println(minMaxSum4.getMin());
        System.out.println(minMaxSum4.getAverage());
        System.out.println(minMaxSum4.getCount());
        System.out.println(minMaxSum4.getSum());
        System.out.println("==========================================================");
        //流不能被重复使用，如果重复使用 Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
        Stream<Integer> stream5 = Stream.iterate(1, item -> item + 2).limit(10);
        System.out.println(stream5);
        stream5.filter(item -> item > 2).forEach(System.out::println);
        System.out.println(stream5.distinct());
        System.out.println("==========================================================");
        Stream<Integer> stream6 = Stream.iterate(1, item -> item + 2).limit(10);
        System.out.println(stream6);
        Stream<Integer> stream7 = stream6.filter(item -> item > 2);
        System.out.println(stream7);
        Stream stream8 = stream7.distinct();
        System.out.println(stream8);


    }
}
