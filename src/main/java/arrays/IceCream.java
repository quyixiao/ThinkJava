//: arrays/IceCream.java
package arrays;
// Returning arrays from methods.

import java.util.Arrays;
import java.util.Random;


/**
 *
 *
 *
 * 436
 *
 *
 * 假设你要写一个方法，而且希望它返回的不止一个值，而是一组值，这对于C和C++
 * 这样的语言来说是有点困难的，因为它们返回的不是一个数组，而只能返回指向数组的指针
 * ，这会造成一些问题，因为它使得控制数组的生命周期变得很困难，并且容易变得很困难
 * 容易造成内存泄漏
 * 在java中，你只是直接返回一个数组，而无需担心要为数组负责，只要你需要它，它就会
 * 一直存在，当你使用完以后，垃圾回收器会清理掉它
 *
 *
 * 方法flavorSet()创建了一个名为result的String数组，此数组容量为n,由传入方的参数
 * 决定，然后从数组中Flavors中随机选择元素，存入results数组中，它是方法所最终的返回
 * 的数组，返回一个数组和返回任何其它的对象没有什么区别，数组是在flavorSet()中被创建
 * ，这一点并不重要，当使用守毕后，垃圾回收器负责清理数组，而只要还需它，此数组就会一直
 * 存在
 *
 * 说句题外话，注意当flavorSet（）每次随机选择数组元素，味道时，它确保不会重复选择，由一个
 * do循环不断进行随机选择，直到找出一个在数组picked 中还不存在的元素，当然还会比较String
 * 以检查随机，直到找出一个在数组picked中还不存在的元素，当然加入数组，然后查找下一个（递增）
 *
 *
 *
 * 1
 *
 */



public class IceCream {
    private static Random rand = new Random(47);
    static final String[] FLAVORS = {
            "Chocolate", "Strawberry", "Vanilla Fudge Swirl",
            "Mint Chip", "Mocha Almond Fudge", "Rum Raisin",
            "Praline Cream", "Mud Pie"
    };

    public static String[] flavorSet(int n) {
        if (n > FLAVORS.length)
            throw new IllegalArgumentException("Set too big");

        String[] results = new String[n];
        boolean[] picked = new boolean[FLAVORS.length];

        for (int i = 0; i < n; i++) {
            int t;
            do
                t = rand.nextInt(FLAVORS.length);
            while (picked[t]);
            results[i] = FLAVORS[t];
            picked[t] = true;
        }
        return results;
    }



    public static void main(String[] args) {
        for (int i = 0; i < 7; i++) {
            System.out.println(Arrays.toString(flavorSet(3)));
        }
    }
}



/* Output:
[Rum Raisin, Mint Chip, Mocha Almond Fudge]
[Chocolate, Strawberry, Mocha Almond Fudge]
[Strawberry, Mint Chip, Mocha Almond Fudge]
[Rum Raisin, Vanilla Fudge Swirl, Mud Pie]
[Vanilla Fudge Swirl, Chocolate, Mocha Almond Fudge]
[Praline Cream, Strawberry, Mocha Almond Fudge]
[Mocha Almond Fudge, Strawberry, Mint Chip]
*///:~
