package com.java8;

import java.util.Arrays;
import java.util.List;


/***
 *  方法引用本质上方法引用看作是一个函数指针 ，function pointer
 *  方法引用是指向另外一个函数的指针
 *
 *  方法引用共分为4类：
 *      有一个容易理解 ，另一个是不容易理解的
 *      1.类名::静态方法名(是jdk8中的新的方法引用)
 *      2.
 *
 *
 *
 */
public class MethodRefernceDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello word");
        list.forEach(item -> System.out.print(item));
        System.out.println();
        list.forEach(System.out::print);





    }
}
