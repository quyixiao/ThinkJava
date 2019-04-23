package java8.comparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/****
 * 新改变的实现
 * 只对字符串
 */
public class ComparatorTest7_comparingInt_length_case_insensitive_order_reversed {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("nihao", "hello", "world", "welcome");
        //先进行第一个比较器的比较，然后，再进行第二次比较器的比较
        Collections.sort(list, Comparator.comparingInt(String::length).thenComparing(String.CASE_INSENSITIVE_ORDER).reversed());
        System.out.println(list);

    }
}
