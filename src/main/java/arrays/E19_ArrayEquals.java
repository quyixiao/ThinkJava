//: arrays/E19_ArrayEquals.java
/****************** Exercise 19 *****************
 * Create a class with an int field that’s initialized
 * from a constructor argument. Create two arrays
 * of these objects, using identical initialization
 * values for each array, and show that Arrays.equals()
 * says that they are unequal. Add an equals() method
 * to your class to fix the problem.
 ************************************************/
package arrays;

import java.util.Arrays;


/*****
 *
 *
 *
 * 452页
 * 创建一个类，它有一个用构造器中的参数初始化的int域，创建由这个类的对象
 * 构成的两个数组，每个数组都使用了相同的初始化值，然后展示它们不相等的Arrays.equals()声明，
 * 在你的类中添加一个equals()方法来解决此问题
 *
 * 1
 *
 */
class DataHolder {
    protected int data;

    DataHolder(int data) {
        this.data = data;
    }
}

class DataHolderWithEquals extends DataHolder {
    DataHolderWithEquals(int data) {
        super(data);
    }

    public boolean equals(Object o) {
        return o instanceof DataHolderWithEquals &&
                data == ((DataHolder) o).data;
    }
}

public class E19_ArrayEquals {
    public static void main(String[] args) {
        DataHolder[] a1 = new DataHolder[5];
        DataHolder[] a2 = new DataHolder[5];
        Arrays.fill(a1, new DataHolder(1));
        Arrays.fill(a2, new DataHolder(1));


        System.out.println(Arrays.equals(a1, a2));
        Arrays.fill(a1, new DataHolderWithEquals(1));
        Arrays.fill(a2, new DataHolderWithEquals(1));
        System.out.println(Arrays.equals(a1, a2));
    }
}