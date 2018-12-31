//: typeinfo/E10_PrimitiveOrTrue.java
/****************** Exercise 10 *****************
 * Write a program to determine whether an array
 * of char is a primitive type or a true object.
 ***********************************************/
package typeinfo;

import static net.mindview.util.Print.print;


/*****
 *
 * 318页
 *  写一个程序，使它能判断char数组是个基本类型，还是一个对象类型
 *
 *      加载，这是由类加载器执行的，该步骤将查的字节码，通常，在classpath所指定的路径中查找，
 *  但这并非是必需的，并从这些字节码中创建一个class对象
 *      链接，在链接阶段的验证类中的字节码，为静态域分配空间，并且如果必需的话，将解析这个类创建的
 *  对其他类的所有引用
 *      初始化，如果该类具有超类，则对其初始化，执行静态初始化器和静态初始化块
 *      初始化被延迟到了对静态方法，构造器隐式的是静态的，或者是非常数静态哉进行首次引用时执行
 *
 *
 *
 */
public class E10_PrimitiveOrTrue {
    public static void main(String args[]) {
        char[] ac = "Hello, World!".toCharArray();
        print("ac.getClass() = " + ac.getClass());
        print("ac.getClass().getSuperclass() = "
                + ac.getClass().getSuperclass());
        char c = 'c';
        //! c.getClass(); // Can't do it, primitives
        // are not true objects.
        int[] ia = new int[3];
        print("ia.getClass() = " + ia.getClass());
        long[] la = new long[3];
        print("la.getClass() = " + la.getClass());
        double[] da = new double[3];
        print("da.getClass() = " + da.getClass());
        String[] sa = new String[3];
        print("sa.getClass() = " + sa.getClass());
        E10_PrimitiveOrTrue[] pot =
                new E10_PrimitiveOrTrue[3];
        print("pot.getClass() = " + pot.getClass());
        // Multi-dimensional arrays:
        int[][][] threed = new int[3][][];
        print("threed.getClass() = " + threed.getClass());
    }
}