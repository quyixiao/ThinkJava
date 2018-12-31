//: arrays/TestGenerated.java
package arrays;

import net.mindview.util.CountingGenerator;
import net.mindview.util.Generated;

import java.util.Arrays;


/**
 *
 *
 * 447
 *
 *
 * collectData 类将，它将创建一个Collection对象，该对象中所填充的元素是由gen产生的，而元素的数量则是由构造器的第二个参数
 * 确定，所有的Collection 子类型都拥有toAayys()方法，该方法将使用Collection中的元素来填充参数数组
 *
 *
 *
 * 1
 */
public class TestGenerated {
    public static void main(String[] args) {
        Integer[] a = {9, 8, 7, 6};

        System.out.println(Arrays.toString(a));

        a = Generated.array(a, new CountingGenerator.Integer());

        System.out.println(Arrays.toString(a));

        Integer[] b = Generated.array(Integer.class, new CountingGenerator.Integer(), 15);

        System.out.println(Arrays.toString(b));
    }
}



/* Output:
[9, 8, 7, 6]
[0, 1, 2, 3]
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
*///:~
