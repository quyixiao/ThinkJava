package java8.spliterator;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class NumCounterTest {
    public static void main(String[] args) {
        String arr = "1%3 200s";
        Stream<Character> stream = IntStream.range(0, arr.length()).mapToObj(arr::charAt);


        System.out.println("ordered total: " + countNum(stream));
        System.out.println("=============================================================");

        Spliterator<Character> spliterator = new NumCounterSpliterator(0, arr, true);
        // 传入true表示是并行流
        Stream<Character> parallelStream = StreamSupport.stream(spliterator, true);
        System.out.println("parallel total: " + countNum(parallelStream));
    }

    private static int countNum(Stream<Character> stream) {
        NumCounter numCounter = stream.reduce(new NumCounter(0, 0, false), NumCounter::accumulate, NumCounter::combine);
        return numCounter.getSum();
    }
}