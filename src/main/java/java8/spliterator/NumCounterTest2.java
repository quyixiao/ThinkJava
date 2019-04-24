package java8.spliterator;

import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class NumCounterTest2 {
    public static void main(String[] args) {
        String arr = "12%3 21sdas s34d dfsdz45 R3 jo34 sjkf8 3$1P 213ikflsd fdg55 kfd";

        Spliterator<Character> spliterator = new NumCounterSpliterator2(0, arr.length(), arr.toCharArray(), true);
        // 传入true表示是并行流
        Stream<Character> parallelStream = StreamSupport.stream(spliterator, true);
        System.out.println("parallel total: " + countNum(parallelStream));
    }

    private static int countNum(Stream<Character> stream) {
        NumCounter numCounter = stream.reduce(new NumCounter(0, 0, false), NumCounter::accumulate, NumCounter::combine);
        return numCounter.getSum();
    }
}