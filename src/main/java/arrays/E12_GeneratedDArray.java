//: arrays/E12_GeneratedDArray.java
/****************** Exercise 12 *****************
 * Create an initialized array of double using
 * CountingGenerator. Print the results.
 ************************************************/
package arrays;

import net.mindview.util.ConvertTo;
import net.mindview.util.CountingGenerator;
import net.mindview.util.Generated;

import java.util.Arrays;


/*****
 * 450页
 *  用CountingGenerator创建一个初始化过的double数组并打印结果
 *
 *
 * 1
 */
public class E12_GeneratedDArray {
    public static void main(String[] args) {
        double[] d = ConvertTo.primitive(Generated.array(Double.class, new CountingGenerator.Double(), 15));
        System.out.println(Arrays.toString(d));
    }
}