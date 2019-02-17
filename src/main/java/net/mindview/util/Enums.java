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
 *2222222222222222222
 *为；冒号早早早是一款                    我就直接ds
 * 567890-=、`=-0铸影响力；'
 * ；axx opikjgrdejksuhjklm546789iodxfcghjkl;'
 *
 *
 *
 * 我们n已经知道，所有的enum都继承自java.lang.enum类。由于java不支持多重继承，所以，你的
 * enum类不能再继承其他类
 *
 *
 * 然而我创建了一个新的enum时，可以同时实现多个接口
 *
 *
 * 在一个接口的内部，创建实现该接口的枚举，以此将元素进行分组，可以达到将枚举元素分类
 * 组织
 *
 *
 *
 *
 *
 * * *
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
