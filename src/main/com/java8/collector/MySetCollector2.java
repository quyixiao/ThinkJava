package com.java8.collector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/***
 * 收集以后，对结果进行增强
 *
 * 1.输入:Set<String>
 * 2.输出:Map<String,String>
 *
 * 示例输入：["hello","world","hello world"]
 * 示例输出：[{hello,hello},{world,world},{hello world,hello world}]
 *
 *
 *
 *
 *
 */
public class MySetCollector2<T> implements Collector<T, Set<T>, Map<T, T>> {


    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supplier invoked!");
        return HashSet::new;
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accumulator invoked!");
        return (set, item) -> {
            System.out.println("accumulator:" + Thread.currentThread().getName() + "," + set + ",item=" + item);
            set.add(item);
        };
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("combiner invoked!");
        return (set, item) -> {
            set.addAll(item);
            return set;
        };
    }


    @Override
    public Function<Set<T>, Map<T, T>> finisher() {

        System.out.println("combiner invoked!");

        return set -> {
            Map<T, T> map = new HashMap<>();
            set.stream().forEach(item -> map.put(item, item));
            return map;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("characteristics invoked!");
        // Exception in thread "main" java.lang.ClassCastException: java.util.HashSet cannot be cast to java.util.Map
        //at com.java8.collector.MySetCollector2.main(MySetCollector2.java:74)
        /**
         * Indicates that the finisher function is the identity function and（）
         * can be elided.  If set, it must be the case that an unchecked cast (如果强制设置的话，就会出现转换异常)
         * from A to R will succeed.（如果转换的话，就会出现不成功）
         */
        // 如果设置了Characteristics.IDENTITY_FINISH方法，那么，就执行finisher ()方法
        //return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED,Characteristics.IDENTITY_FINISH));

        /**
         * Indicates that this collector is <em>concurrent</em>, meaning that
         * the result container can support the accumulator function being （如果加上CONCURRENT，表示多个线程操作一个结果容器,combiner将不会调用）
         * called concurrently with the same result container from multiple
         * threads.
         *
         * <p>If a {@code CONCURRENT} collector is not also {@code UNORDERED}, (如果不加上CONCURRENT表示多个线程操作多个结果容器，combiner将会调用)
         * then it should only be evaluated concurrently if applied to an
         * unordered data source.
         *
         * 下面代码将抛出 ConcurrentModificationException exception
         * 对于Collections静态工厂类来说，其实共分成两种情况，
         * 1.通过CollectorImpl来实现
         * 2.通过reducing方法来实现：reducing方法本身又是通过CollectorImpl来实现的
         * 归根结底是通过CollectorImpl来实现的
         *
         * hello ,world,welcome
         * ahello,world,welcome
         *
         *
         */
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED, Characteristics.CONCURRENT));
        //return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED));
    }


    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            List<String> list = Arrays.asList("hello", "world", "welcome", "hello", "a", "b", "c", "d", "e", "f", "g");
            Set<String> set = new HashSet<>();
            set.addAll(list);
            System.out.println("set :" + set);
            //{a=a, b=b, world=world, c=c, d=d, e=e, f=f, g=g, hello=hello, welcome=welcome}
            // Map<String, String> map = set.stream().collect(new MySetCollector2<>());
            //{a=a, b=b, world=world, c=c, d=d, e=e, f=f, g=g, hello=hello, welcome=welcome}
            Map<String, String> map = set.parallelStream().collect(new MySetCollector2<>());
            System.out.println(map);
        }

    }
}