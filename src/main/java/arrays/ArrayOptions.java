//: arrays/ArrayOptions.java
package arrays;
// Initialization & re-assignment of arrays.

import java.util.Arrays;

import static net.mindview.util.Print.print;


/**
 *
 *
 * 434
 * 数组a是一个尚未初始化的局部变量，在你对它正确了初始化之前，编译器不允许用此引用
 * 做任何事情，数组b初始化为指向一个BerryllinuSphere引用的数组，但其实并没有BeryllinumSphere
 * 对象放置到数组中，然而，然而仍然可以这个询问数组的大小，加为b指向一个合法的对象，这样做有一个
 * 小缺点，你无法知道数组中有多少个元素，因为length只表示元素的个数，新生成了一个数组对象
 * 时，其中所有的引用被自动初始化为null，所以检查其中的引用是否为null ，即可知道数组的
 * 某个位置是否有对象，同样的，基本类型的数组如果是数值类型的，就被自动化为 0 ，如果是字符型
 * 的，就被自动初始化为，如果是布尔型，就被自动初始化为false
 * <p>
 * <p>
 * 数组c表明，数组对旬在创建之后，随即将数组的各个位置都赋值为BeryllinumSphere对象，数组d表明使用
 * "聚集初始化"语法创建数组对象，隐式的使用new在堆中创建，数组e 一样，并且以BeryllinumSphere对象
 * 将其初始化可以看，这些操作只用了一条语句
 *
 *
 *
 * 1
 */
public class ArrayOptions {
    public static void main(String[] args) {
        // Arrays of objects:
        BerylliumSphere[] a; // Local uninitialized variable
        BerylliumSphere[] b = new BerylliumSphere[5];
        // The references inside the array are
        // automatically initialized to null:
        print("b: " + Arrays.toString(b));
        System.out.println("==================================");

        BerylliumSphere[] c = new BerylliumSphere[4];
        for (int i = 0; i < c.length; i++)
            if (c[i] == null) // Can test for null reference
                c[i] = new BerylliumSphere();
        // Aggregate initialization:
        BerylliumSphere[] d = {new BerylliumSphere(),
                new BerylliumSphere(), new BerylliumSphere()
        };

        // Dynamic aggregate initialization:
        a = new BerylliumSphere[]{
                new BerylliumSphere(), new BerylliumSphere(),
        };

        // (Trailing comma is optional in both cases)
        print("a.length = " + a.length);
        print("b.length = " + b.length);
        print("c.length = " + c.length);
        print("d.length = " + d.length);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
        a = d;
        print("a.length = " + a.length);
        System.out.println("============================================");

        // Arrays of primitives:
        int[] e; // Null reference
        int[] f = new int[5];
        // The primitives inside the array are
        // automatically initialized to zero:
        print("f: " + Arrays.toString(f));
        int[] g = new int[4];
        for (int i = 0; i < g.length; i++)
            g[i] = i * i;
        int[] h = {11, 47, 93};
        // Compile error: variable e not initialized:
        //!print("e.length = " + e.length);
        print("f.length = " + f.length);
        print("g.length = " + g.length);
        print("h.length = " + h.length);
        System.out.println("-------------" );
        e = h;
        print("e.length = " + e.length);
        e = new int[]{1, 2};
        print("e.length = " + e.length);
    }
}



/* Output:
b: [null, null, null, null, null]
a.length = 2
b.length = 5
c.length = 4
d.length = 3
a.length = 3
f: [0, 0, 0, 0, 0]
f.length = 5
g.length = 4
h.length = 3
e.length = 3
e.length = 2
*///:~
