package com.java8;

import java.util.function.Function;

/***
 * 高阶函数，如果一个函数接收一个函数作为参数，或者返回一个函数作为函数，那么我们称这是一个高阶函数
 *
 */
public class FunctionTest {

    public static void main(String[] args) {
        FunctionTest test = new FunctionTest();
        int a = 2;
        System.out.println(test.compute(a, value -> value * value));
        System.out.println(test.function(a, (Function<Integer, Integer>) value -> 2 * 2 * 2 * value));
        System.out.println(test.function(a, (Function<Integer, Integer>) value -> 5 + value));
        System.out.println(test.function(a, (Function<Integer, Integer>) value -> value * value));
        System.out.println(test.function(a, (Function<Integer, String>) value -> a + "HellowWord"));


        System.out.println(test.function1(a, (Function<Integer, String>) value -> a + "HellowWord"));


    }

    public int compute(int a, Function<Integer, Integer> function) {
        int result = function.apply(a);
        return result;
    }

    public <T> T function(int a, Function<Integer, T> function) {
        return function.apply(a);
    }

    public <T, R> R function1(T a, Function<T, R> function) {
        return function.apply(a);
    }


}
