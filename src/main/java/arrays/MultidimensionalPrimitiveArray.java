//: arrays/MultidimensionalPrimitiveArray.java
package arrays;
// Creating multidimensional arrays.

import java.util.Arrays;


/**
 *
 * 437
 * 每对花括号括起来的集合都会把你带到下一级数组
 *
 *
 * 1
 */
public class MultidimensionalPrimitiveArray {
    public static void main(String[] args) {
        int[][] a = {
                {1, 2, 3,},
                {4, 5, 6,},
        };
        System.out.println(Arrays.deepToString(a));
    }
}



/* Output:
[[1, 2, 3], [4, 5, 6]]
*///:~
