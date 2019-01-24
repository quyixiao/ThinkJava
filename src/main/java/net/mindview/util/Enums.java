//: net/mindview/util/Enums.java
package net.mindview.util;

import java.util.Random;


/****
 * 596页
 *
 * <T extends Enum<T>> 表示T是一个enum实例，而将Class<T>作为参数的话，
 * 我们就可能利用Class对象得到enum实例数组了，重载后的random()方法只需要使用T[]作为参数
 * ，因为它并不会调用enum上的任何操作，它只需要从数组中随机选择一个元素即可，这样，最终
 * 返回类型正是enum的类型。
 *
 *
 *
 *
 * 1
 *
 */
public class Enums {
    private static Random rand = new Random(47);

    public static <T extends Enum<T>> T random(Class<T> ec) {
        return random(ec.getEnumConstants());
    }

    public static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }
} ///:~
