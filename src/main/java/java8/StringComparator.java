package java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/***
 *
 * Java Lambda表达式，是一种匿名的函数，它是没有声明方法的，即没有访问修饰符，返回值声明和名字
 * Lambda表达式作用：会弹行为，而不仅仅是值，提升抽象层次，API可用性好
 * Java Lambda基本语法 Java中的Lambda表达式的基本语法，(argument)->(body) 比如说 (arg1,arg2 ...)->{body} ,{type1 arg1,type2 arg2 ...}{body}
 * Lambda示例说明
 *  (int a ,int b )->{return a + b ;}
 *  ()->System.out.println("Hello world");
 *  (String s)->{System.out.println(s);}
 *  ()->42
 *  ()->{return 3.141592563};
 *  s -> System.out.print("hello world");
 *
 *
 *  Java Lambda结构
 *      一个Lambda表达式可以有零个或多个参数
 *      参数类型既可以明确声明，也可以根据上下文来推断，例如：（int a）与（a）效果相同
 *      所有的参数需要包含在圆括号内，参数之间用逗号相隔，例如：（a,b）或（int a,int b ）或（String a,int b,float c）
 *      空括号代表参数
 *      当只有一个参数，且其类型可推导时，圆括号（）可以省略，例如 ： a -> return a * a
 *      Lambda表达式的主体可以包含零条或多条语句
 *      如果Lambda表达式的主体只有一条语句，花括号{}可省略，匿名函数的返回类型与该主体表达式一致
 *      如果Lambda表达式的主体包含一条以上的语句，则表达式必须在花括号{}中，（形成代码块），匿名函数的返回类型与代码块的返回类型一致，若没有返回则返回为空
 *
 *
 *
 *
 *
 */
public class StringComparator {


    public static void main(String[] args) {
        List<String> name = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu");

        Collections.sort(name, (o1, o2) -> o2.compareTo(o1));
        System.out.println(name);

        System.out.println("==================================");
        Collections.sort(name, Comparator.reverseOrder());
        System.out.println("==================================");
        Collections.sort(name, (String o1, String o2) -> {
            return o2.compareTo(o1);
        });

        System.out.println("==================================");
        Collections.sort(name, (String o1, String o2) -> o2.compareTo(o1));


    }


}
