package java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class StreamTest11_parallelStream_sorted {

    public static void main(String[] args) {
        long a = System.nanoTime();
        List<String> list = new ArrayList<>(5000000);
        for (int i = 0; i < 5000000; i++) {
            list.add(UUID.randomUUID().toString());
        }
        long b = System.nanoTime();
        System.out.println(TimeUnit.NANOSECONDS.toMillis(b - a));
        System.out.println("开始排序");
        //表示一个线程来做所有的操作
        list.stream().sorted().count();
        long c = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(c - b);
        System.out.println(millis);
        //表示通过多个线程来执行所有的操作
        list.parallelStream().sorted().count();
        long d = System.nanoTime();
        System.out.println(TimeUnit.NANOSECONDS.toMillis(d - c));
    }
}
