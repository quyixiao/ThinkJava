//: arrays/E06_Filling2DArray.java
/****************** Exercise 6 *****************
 * Write a method that takes two int arguments,
 * indicating the two sizes of a 2-D array. The
 * method should create and fill a 2-D array of
 * BerylliumSphere according to the size arguments.
 ***********************************************/
package arrays;

import java.util.Arrays;


/*****
 * 440页
 *      编写一个方法，它接受两个表示二维数组尺寸的int参数，这个方法者应该这两个
 * 根据尺寸参数，创建并填充一个BerylliumSphere二维数组
 *
 *
 * 1
 *
 */
public class E06_Filling2DArray {
    static BerylliumSphere[][] fill(int xLen, int yLen) {
        BerylliumSphere[][] a = new BerylliumSphere[xLen][yLen];
        for (int i = 0; i < xLen; i++)
            for (int j = 0; j < yLen; j++)
                a[i][j] = new BerylliumSphere();
        return a;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(fill(3, 3)));
    }
}