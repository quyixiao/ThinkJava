//: arrays/E18_ArrayCopy.java
/****************** Exercise 18 *****************
 * Create and fill an array of BerylliumSphere.
 * Copy this array to a new array and show that it’s
 * a shallow copy.
 ************************************************/
package arrays;

import java.lang.reflect.Field;
import java.util.Arrays;

import static net.mindview.util.Print.print;


/*****
 *
 *
 *
 *
 * 451页
 *
 * 创建并填充一个BerylliumSphere数组，疳这个数组复制到一个新的数组中，并展示这是一种
 * 浅复制
 *
 *
 * 1
 */
public class E18_ArrayCopy {
    // BerylliumSphere.id is private, so we need to use
    // reflection to alter its value.
    static void setID(BerylliumSphere bs, long value) {
        try {
            Field fid = BerylliumSphere.class.getDeclaredField("id");
            fid.setAccessible(true);
            fid.setLong(bs, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BerylliumSphere[] a = new BerylliumSphere[4];
        Arrays.fill(a, new BerylliumSphere());
        BerylliumSphere[] b = new BerylliumSphere[4];
        print("a = " + Arrays.toString(a));
        print("b = " + Arrays.toString(b));

        System.arraycopy(a, 0, b, 0, b.length);
        print("b = " + Arrays.toString(b));
        // Changing a reference in 'a' will not impact 'b'.
        a[1] = a[3] = new BerylliumSphere();

        print("a = " + Arrays.toString(a));
        print("b = " + Arrays.toString(b));
        // Changing an object's state will impact 'b', as well.
        setID(a[0], -1L);
        print("a = " + Arrays.toString(a));
        print("b = " + Arrays.toString(b));
    }
}