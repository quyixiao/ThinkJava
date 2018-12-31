//: arrays/RaggedArray.java
package arrays;

import java.util.Arrays;
import java.util.Random;


/**
 * 438
 * <p>
 * 你可以看到基本类型的数组的值在不进行显式的初始化的情况下，会被自动的初始化，对象数组会被初始化为null
 * 数组中构成矩的每一个向量都可以具有任意长度（这个被称为粗糙的数组）
 * 第一个new创建出来的数组，其第一维的长度，直到碰到第三个new,第三维的长度才得以确定，可以用类似的
 * 方式处理非基本类型的对象数组，下面，你可以看到如何花括号把多个new 表达式组织在一起：
 * <p>
 * <p>
 * <p>
 * 1
 */
public class RaggedArray {
    public static void main(String[] args) {
        Random rand = new Random(47);
        // 3-D array with varied-length vectors:
        int[][][] a = new int[rand.nextInt(7)][][];
        System.out.println("========"+a.length);
        for (int i = 0; i < a.length; i++) {
            a[i] = new int[rand.nextInt(5)][];
            System.out.println("---------"+a[i].length);
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = new int[rand.nextInt(5)];
                System.out.println("++++++++"+a[i][j].length);
            }
        }
        System.out.println(Arrays.deepToString(a));
    }
} /* Output:
[[], [[0], [0], [0, 0, 0, 0]], [[], [0, 0], [0, 0]], [[0, 0, 0], [0], [0, 0, 0, 0]], [[0, 0, 0], [0, 0, 0], [0], []], [[0], [], [0]]]
*///:~
