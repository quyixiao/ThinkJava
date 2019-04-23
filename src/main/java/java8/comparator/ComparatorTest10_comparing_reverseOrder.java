package java8.comparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/****
 * 新改变的实现
 * 只对字符串
 */
public class ComparatorTest10_comparing_reverseOrder {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("nihao", "hello", "world", "welcome");
        //忽略大小写，倒序排序
        Collections.sort(list, Comparator.comparingInt(String::length).reversed().thenComparing(
                Comparator.comparing(String::toLowerCase, Comparator.reverseOrder())
        ));

        System.out.println(list);

    }
}
