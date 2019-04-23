package java8.web;

import java.util.ArrayList;
import java.util.List;

/***
 * 实例中我们将System.out::println 方法作为静态方法来引用。
 * 三、Java 8 函数式接口
 函数式接口(FunctionalInterface)就是一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口。

 函数式接口可以被隐式转换为lambda表达式。

 函数式接口可以现有的函数友好地支持 lambda。

 JDK 1.8之前已有的函数式接口:

 */
public class Java8Tester_forEach {
    public static void main(String args[]) {
        List names = new ArrayList();
        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");
        names.forEach(System.out::println);
    }
}
