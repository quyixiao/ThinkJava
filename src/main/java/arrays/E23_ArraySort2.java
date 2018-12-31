//: arrays/E23_ArraySort2.java
/****************** Exercise 23 *****************
 * Create an array of Integer, fill it with random
 * int values (using autoboxing), and sort it into
 * reverse order using a Comparator.
 ************************************************/
package arrays;

import net.mindview.util.Generated;
import net.mindview.util.Generator;
import net.mindview.util.RandomGenerator;

import java.util.Arrays;
import java.util.Collections;

import static net.mindview.util.Print.print;


/****
 *
 *
 *
 * 457页
 *
 * 创建一个数组，用随机的int数字填充它，使用自动包装机制，再使用Comparetor，将其进行反向排序。
 *
 *
 * 1
 *
 *
 */



public class E23_ArraySort2 {
    public static void main(String[] args) {
        Generator<Integer> gen = new RandomGenerator.Integer(1000);
        Integer[] a = Generated.array(new Integer[25], gen);
        print("Unsorted array: " + Arrays.toString(a));
        Arrays.sort(a);
        print("Sorted array: " + Arrays.toString(a));
    }
}