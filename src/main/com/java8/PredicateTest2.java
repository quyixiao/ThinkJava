package com.java8;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class PredicateTest2 {

    public static void main(String[] args) {
        PredicateTest2 test2 = new PredicateTest2();
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //打印所有小于3的数
        conditionFilter(list, p -> p > 3);
        //打印大于5的数据
        conditionFilter(list, p -> p <= 5);
        //打印所有的偶数
        conditionFilter(list, p -> p % 2 == 0);
        //打印所有的基数
        conditionFilter(list, p -> p % 2 == 1);
        //打印所有的数据
        conditionFilter(list, p -> true);
        //不打印所有的数据
        conditionFilter(list, p -> false);
        //大于5并且是偶数 数字
        conditionFilter(list, p -> p >= 5, p -> p % 2 == 0);

        //抽象化函数方法
        System.out.println(test2.isEqual("test").test("test"));
        //用控制方法
        System.out.println(Predicate.isEqual(new Date()).test(new Date()));

    }

    public static void conditionFilter(List<Integer> list, Predicate<Integer> predicate) {
        list.forEach(i -> {
            if (predicate.test(i)) {
                System.out.println(i);
            }
        });
        System.out.println("=============================");
    }


    public static void conditionFilter(List<Integer> list, Predicate<Integer> predicate1, Predicate<Integer> predicate2) {
        list.forEach(i -> {
            if (predicate1.test(i) && predicate2.test(i)) {
                System.out.println(i);
            }
        });
        System.out.println("=============================");
    }


    public Predicate<String> isEqual(Object object) {
        return Predicate.isEqual(object);
    }


}
