//: arrays/AssemblingMultidimensionalArrays.java
package arrays;
// Creating multidimensional arrays.

import java.util.Arrays;

/**
 *
 * 439
 * 下面的示例展示了如何的逐步的去创建一个二维数组，部分的构建一个非基本类型的对象数组
 *
 *
 * 1
 */
public class AssemblingMultidimensionalArrays {
    public static void main(String[] args) {
        Integer[][] a = new Integer[3][];
        for (int i = 0; i < a.length; i++) {
            a[i] = new Integer[3];
            for (int j = 0; j < a[i].length; j++)
                a[i][j] = i * j; // Autoboxing
        }
        System.out.println(Arrays.deepToString(a));
    }
} /* Output:
[[0, 0, 0], [0, 1, 2], [0, 2, 4]]
*///:~
