//: arrays/ThreeDWithNew.java
package arrays;

import java.util.Arrays;


/****
 * 438
 *
 * 下面的三维数组就是在new表达式中分配的
 *
 *
 *
 * 1
 */
public class ThreeDWithNew {
    public static void main(String[] args) {
        // 3-D array with fixed length:
        int[][][] a = new int[2][2][4];
        System.out.println(Arrays.deepToString(a));
    }
}







/* Output:
[[[0, 0, 0, 0], [0, 0, 0, 0]], [[0, 0, 0, 0], [0, 0, 0, 0]]]
*///:~
