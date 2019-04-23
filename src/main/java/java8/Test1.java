package java8;

import java.util.Arrays;
import java.util.List;


/***
 * 1.如果一个接口只有一个抽象方法，那么接口就是一个函数式接口
 * 2.如果我们在某个接口声明了FuntionalInterface注解，那么编译器就会按照函数式接口来定义来要求该接口
 * 3.如果某个接口只有一个抽象方法，但是我们并没有给该接口声明FunctionalInterface注解，那么，编译器依旧会将该接口看作是函数式接口
 * 4.接口中有默认方法
 * 5.
 *
 *
 *
 */
public class Test1 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("--------------------------------");
        for (Integer i : list) {
            System.out.println(i);
        }

        System.out.println("++++++++++++++++++++++++++++++++++");

        //函数式编程
        list.forEach(i -> System.out.println(i));
        //函数式编程
        list.forEach((Integer i) -> System.out.println(i));
        //函数式接口有一个直观的，更加好的方式了
        //Lambda表达式作用
        //Lambda表达式为java添加了缺失的函数式编程特性，使我们能将函数当作一等一的公民看待
        //在将函数作变一等一公民的语言中，Lambda表达式的类型是函数，但是java中，Lambda表达式对象，他们必须依附于一类特别的对象类型->函数式接口（Functional Interface）
        //外部
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        for (Integer i : list1) {
            System.out.println(i);
        }

        //方法引用 method reference idea 能够识别的
        list.forEach(System.out::println);


    }

}
