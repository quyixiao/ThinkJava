//: arrays/ComparingArrays.java
package arrays;
// Using Arrays.equals()

import java.util.Arrays;

import static net.mindview.util.Print.print;


/**
 * 最初，a1 与 a2 完全相等，所以输出为true, 然后改变其中的一个元素，使得结果为false, 在最上面
 * 后一个例子中，s1的所有的元素都指向同一个对象，而数组s2 是五个相互独立的对象，然而数组相等是基于
 * 内容的，所以结果都是false.
 */
public class ComparingArrays {
    public static void main(String[] args) {
        int[] a1 = new int[10];
        int[] a2 = new int[10];


        Arrays.fill(a1, 47);
        Arrays.fill(a2, 47);

        print(Arrays.equals(a1, a2));

        a2[3] = 11;

        print(Arrays.equals(a1, a2));


        String[] s1 = new String[4];


        Arrays.fill(s1, "Hi");

        String[] s2 = {new String("Hi"), new String("Hi"),
                new String("Hi"), new String("Hi")};


        print(Arrays.equals(s1, s2));
    }
} /* Output:
true
false
true
*///:~
