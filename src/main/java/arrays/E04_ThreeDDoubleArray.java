//: arrays/E04_ThreeDDoubleArray.java
/****************** Exercise 4 *****************
 * Repeat the previous exercise for a
 * three-dimensional array.
 ***********************************************/
package arrays;

import java.util.Locale;

import static net.mindview.util.Print.print;


/****
 *
 * 440
 *      编写一个方法，能够产生二维双精度数组并加以初始化，数组的容量由方法的
 *  形式参数决定，其初值必须落在另外两个形式参数所指定的区间之内，为编写第二个方法，
 *  打印出第一个方法所产生的数组，在main()中通过产生不同容量的数组的并打印其内容来
 *  测试这两个方法
 *
 *
 *
 * 1
 *
 *
 */
public class E04_ThreeDDoubleArray {
    public static double[][][] threeDDoubleArray(int xLen, int yLen, int zLen,
            double valStart, double valEnd) {
        double[][][] array = new double[xLen][yLen][zLen];
        double increment = (valEnd - valStart) / (xLen * yLen * zLen);
        double val = valStart;
        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array[i].length; j++)
                for (int k = 0; k < array[i][j].length; k++) {
                    array[i][j][k] = val;
                    val += increment;
                }
        return array;
    }

    public static void printArray(double[][][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                for (int k = 0; k < array[i][j].length; k++)
                    System.out.printf(Locale.US, "%.2f ", array[i][j][k]);
                print();
            }
            print();
        }
    }

    public static void main(String args[]) {
        double[][][] threeD =
                threeDDoubleArray(4, 6, 2, 47.0, 99.0);
        printArray(threeD);
        print("**********************");
        double[][][] threeD2 =
                threeDDoubleArray(2, 2, 5, 47.0, 99.0);
        printArray(threeD2);
        print("**********************");
        double[][][] threeD3 =
                threeDDoubleArray(9, 5, 7, 47.0, 99.0);
        printArray(threeD3);
    }
}