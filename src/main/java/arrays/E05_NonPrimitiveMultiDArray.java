//: arrays/E05_NonPrimitiveMultiDArray.java
/****************** Exercise 5 *****************
 * Demonstrate that multidimensional arrays of
 * non-primitive types are automatically initialized
 * to null.
 ***********************************************/
package arrays;

import java.util.Arrays;


/*****
 *
 * 440
 *  证明基本类型的数组会自动初始化为null
 *
 *
 * 1
 */
public class E05_NonPrimitiveMultiDArray {
    public static void main(String[] args) {
        System.out.println(
                Arrays.deepToString(new Object[3][3][3]));
    }
}