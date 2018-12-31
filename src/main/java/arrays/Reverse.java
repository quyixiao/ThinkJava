//: arrays/Reverse.java
package arrays;
// The Collections.reverseOrder() Comparator

import net.mindview.util.Generated;

import java.util.Arrays;
import java.util.Collections;

import static net.mindview.util.Print.print;


/**
 *
 *
 * 453
 *
 *
 *
 * 它可以自然的排序有顺序，这个很容易应用于CompType
 *
 *
 *
 * 1
 *
 */
public class Reverse {
    public static void main(String[] args) {
        CompType[] a = Generated.array(new CompType[12], CompType.generator());

        print("before sorting:");

        print(Arrays.toString(a));

        Arrays.sort(a, Collections.reverseOrder());

        print("after sorting:");


        print(Arrays.toString(a));
    }
}







/* Output:
before sorting:
[[i = 58, j = 55], [i = 93, j = 61], [i = 61, j = 29]
, [i = 68, j = 0], [i = 22, j = 7], [i = 88, j = 28]
, [i = 51, j = 89], [i = 9, j = 78], [i = 98, j = 61]
, [i = 20, j = 58], [i = 16, j = 40], [i = 11, j = 22]
]
after sorting:
[[i = 98, j = 61], [i = 93, j = 61], [i = 88, j = 28]
, [i = 68, j = 0], [i = 61, j = 29], [i = 58, j = 55]
, [i = 51, j = 89], [i = 22, j = 7], [i = 20, j = 58]
, [i = 16, j = 40], [i = 11, j = 22], [i = 9, j = 78]
]
*///:~
