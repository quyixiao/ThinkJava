//: arrays/StringSorting.java
package arrays;
// Sorting an array of Strings.

import net.mindview.util.Generated;
import net.mindview.util.RandomGenerator;

import java.util.Arrays;
import java.util.Collections;

import static net.mindview.util.Print.print;

/**
 *
 * 455
 * 注意：String 排序算法依据词典编排排序，所以大写的字母开头的词都放在前面输出，然后才是
 * 小写字母开头的词，如果你想忽略大小写字母，如果想忽略大小写字母将单词都放在一起排序，那么
 * 可以像上例中的最后一个sort()的调用一样，使用String,Case_insensitive_order
 * java 标准类库中的排序算法针对象设计的"稳定归并排序"，所以无须担心排序的性能，除非你
 * 确定排序部分确实是程序效率的瓶颈
 *
 *
 * 1
 *
 *
 *
 */
public class StringSorting {
    public static void main(String[] args) {
        String[] sa = Generated.array(new String[20], new RandomGenerator.String(5));
        print("Before sort: " + Arrays.toString(sa));
        Arrays.sort(sa);
        print("After sort: " + Arrays.toString(sa));
        Arrays.sort(sa, Collections.reverseOrder());
        print("Reverse sort: " + Arrays.toString(sa));
        Arrays.sort(sa, String.CASE_INSENSITIVE_ORDER);
        print("Case-insensitive sort: " + Arrays.toString(sa));
    }
} /* Output:
Before sort: [YNzbr, nyGcF, OWZnT, cQrGs, eGZMm, JMRoE, suEcU, OneOE, dLsmw, HLGEa, hKcxr, EqUCB, bkIna, Mesbt, WHkjU, rUkZP, gwsqP, zDyCy, RFJQA, HxxHv]
After sort: [EqUCB, HLGEa, HxxHv, JMRoE, Mesbt, OWZnT, OneOE, RFJQA, WHkjU, YNzbr, bkIna, cQrGs, dLsmw, eGZMm, gwsqP, hKcxr, nyGcF, rUkZP, suEcU, zDyCy]
Reverse sort: [zDyCy, suEcU, rUkZP, nyGcF, hKcxr, gwsqP, eGZMm, dLsmw, cQrGs, bkIna, YNzbr, WHkjU, RFJQA, OneOE, OWZnT, Mesbt, JMRoE, HxxHv, HLGEa, EqUCB]
Case-insensitive sort: [bkIna, cQrGs, dLsmw, eGZMm, EqUCB, gwsqP, hKcxr, HLGEa, HxxHv, JMRoE, Mesbt, nyGcF, OneOE, OWZnT, RFJQA, rUkZP, suEcU, WHkjU, YNzbr, zDyCy]
*///:~
