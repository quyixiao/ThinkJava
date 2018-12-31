//: initialization/E17_ObjectReferences.java
/****************** Exercise 17 *****************
 * Create a class with a constructor that takes
 * a String argument. During construction, print
 * the argument. Create an array of object
 * references to this class, but don't
 * create objects to assign into the
 * array. When you run the program, notice
 * whether the initialization messages from the
 * constructor calls are printed.
 ************************************************/
package initialization;


/**
 *
 * 101页
 *      创建一个类，它有一个的的接受一个元素的构造器，在构造阶段，打印该
 *  参数，创建一个该类的对象引用数组，但是不实际去创建赋值给该数组，当运行程序时，请
 *  注意来自该构造器调用中的初始第消息是否打印出来。
 *
 */
class Test {
    Test(String s) {
        System.out.println("String constructor; s = " + s);
    }
}

public class E17_ObjectReferences {
    // You can define the array as a field in the class:
    Test[] array1 = new Test[5];

    public static void main(String args[]) {
        // Or as a temporary inside main:
        Test[] array2 = new Test[5];
    }
} 