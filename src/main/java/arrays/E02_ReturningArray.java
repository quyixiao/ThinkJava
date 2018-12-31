//: arrays/E02_ReturningArray.java
/****************** Exercise 2 ******************
 * Write a method that takes an int argument and
 * returns an array of that size, filled with
 * BerylliumSphere objects.
 ************************************************/
package arrays;

import java.util.Arrays;


/*****
 *
 *
 *
 * 437页
 *      编写一个方法，它接受一个int参数，并返回一个具有该尺寸的数组，用BerylliumSphere对象填充
 *  该数组
 */
public class E02_ReturningArray {
    static BerylliumSphere[] createArray(int size) {
        BerylliumSphere[] a = new BerylliumSphere[size];
        for (int i = 0; i < size; i++)
            a[i] = new BerylliumSphere();
        return a;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(createArray(10)));
    }
}