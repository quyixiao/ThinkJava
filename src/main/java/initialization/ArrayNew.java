package initialization;//: initialization/ArrayNew.java
// Creating arrays with new.

import java.util.Arrays;
import java.util.Random;

import static net.mindview.util.Print.print;


/*****
 * 100页
 * 1
 */
public class ArrayNew {
    public static void main(String[] args) {
        int[] a;
        Random rand = new Random(47);
        a = new int[rand.nextInt(20)];
        print("length of a = " + a.length);
        print(Arrays.toString(a));
    }
} /* Output:
length of a = 18
[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
*///:~
