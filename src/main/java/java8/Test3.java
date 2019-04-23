package java8;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@FunctionalInterface
interface TheInterface {

    void myMethod();

}

@FunctionalInterface
interface TheInterface2 {

    void myMethod2();

}


public class Test3 {

    public static void main(String[] args) {
        TheInterface i1 = () -> {

        };
        System.out.println(i1.getClass().getInterfaces()[0]);

        TheInterface2 i2 = () -> {

        };
        System.out.println(i2.getClass().getInterfaces()[0]);

        //线程要执行的代码，//说明这个方法是得到了执行
        new Thread(() -> System.out.println("123")).start();

        //
        List<String> list = Arrays.asList("hello", "word", "hello word");
        list.forEach(item -> {
            System.out.println(item.toUpperCase());
        });

        // 将list1中的元素存入list2并且保存
        List<String> list2 = new ArrayList<>();         //diamond
        list.forEach(item -> list2.add(item.toUpperCase()));
        list2.forEach(System.out::println);
        //新的方式 stream 他是一个串行流 ，paralleStream() 表示一个并行流，一般效率会高一点


        //将元素转化为大写，同时打印数据
        list.stream().map(item -> item.toUpperCase()).forEach(System.out::println);

        //通过方法引用的方法
        list.stream().map(String::toUpperCase).forEach(System.out::println);

        //
        Function<String, String> function = String::toUpperCase;
        System.out.println(function.getClass().getInterfaces());

    }


}
