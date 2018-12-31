//: arrays/ArraySearching.java
package arrays;
// Using Arrays.binarySearch().

import net.mindview.util.ConvertTo;
import net.mindview.util.Generated;
import net.mindview.util.Generator;
import net.mindview.util.RandomGenerator;

import java.util.Arrays;

import static net.mindview.util.Print.print;

/**
 *
 *
 *
 * 454
 *
 *
 * 在while 循环中随机生成了一些值作为查找对象，直到查找到下一个值才停止循环
 *
 *
 *
 *
 * 1
 *
 */
public class ArraySearching {
    public static void main(String[] args) {
        Generator<Integer> gen = new RandomGenerator.Integer(1000);
        int[] a = ConvertTo.primitive(Generated.array(new Integer[25], gen));
        Arrays.sort(a);
        print("Sorted array: " + Arrays.toString(a));
        while (true) {
            int r = gen.next();
            System.out.println(r);
            int location = Arrays.binarySearch(a, r);
            if (location >= 0) {
                print("Location of " + r + " is " + location +
                        ", a[" + location + "] = " + a[location]);
                break; // Out of while loop
            }
        }
    }
} /* Output:
Sorted array: [128, 140, 200, 207, 258, 258, 278, 288, 322, 429, 511, 520, 522, 551, 555, 589, 693, 704, 809, 861, 861, 868, 916, 961, 998]
Location of 322 is 8, a[8] = 322
*///:~
