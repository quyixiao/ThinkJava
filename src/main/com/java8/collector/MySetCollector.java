package com.java8.collector;


import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/***
 * 自己定义一个setCollector
 *
 * @param <T>
 */
public class MySetCollector<T> implements Collector<T, Set<T>, Set<T>> {

    /***
     * 提供一个空的容器
     * @return
     */
    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supplier invoked");
        //return TreeSet<T>::new;
        return HashSet<T>::new;
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accumulator invoked !");
        //return HashSet<T>::add; 是不可以的
        //return (set,item) -> set.add(item);
        return Set<T>::add;
    }

    /***
     * 两相集合进行合并
     * @return
     */
    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("combiner invoked!");
        return (set1,set2)->{
            set1.addAll(set2);
            return set1;
        };
    }

    /****
     *
     * @return
     */
    @Override
    public Function<Set<T>, Set<T>> finisher() {
        System.out.println("finisher invoked!");
        //return t -> t ;
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("characteristics invoked!");
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello","world","welconme","hello");
        Set<String> set = list.stream().collect(new MySetCollector<>());
        System.out.println(set);
    }


}
