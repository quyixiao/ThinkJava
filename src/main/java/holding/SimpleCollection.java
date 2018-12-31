package holding;//: holding/SimpleCollection.java

import java.util.ArrayList;
import java.util.Collection;


/*****
 * Collection，是一个独立的元素的序列，这些元素都服从一条或多条规则，List 必须按照插入的
 * 顺序保存元素，而Seet不能有重复元素，Queue按照排队规则来确定对象的顺序，通常与它们插入的
 * 顺序不同
 *
 * Map ,一组成对的键值对，对象，允许你使用键来查找值,ArrayList允许我们使用另一个对象来查找
 * 某个对象，它也被称为关联数组，因为它将某些对象与另外一些关联在一起的，或者被称为字典的，因为
 * 你可以使用键值对象来查找值，就像在字典中使用单词来定义一样，
 * Map是强大的编程工具
 *
 */
public class SimpleCollection {
    public static void main(String[] args) {
        Collection<Integer> c = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++)
            c.add(i); // Autoboxing
        for (Integer i : c)
            System.out.print(i + ", ");
    }
} /* Output:
0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
*///:~
