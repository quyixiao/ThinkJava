//: holding/E07_TestList.java
/****************** Exercise 7 *****************
 * Create a class and make an initialized array
 * of your class objects. Fill a List from
 * your array. Create a subset of your List using
 * subList(), then remove this subset from
 * your List.
 ***********************************************/
package holding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*****
 *
 *
 * 226
 *      创建一个类，然后创建一个用你的类的对象进行过初始化的数组，通过使用
 *  subList()方法，创建你的List子集，然后你的List中移除这个子集
 *
 *
 * 1
 *
 */
class IDClass {
    private static int counter;
    private int count = counter++;

    public String toString() {
        return "IDClass " + count;
    }
}

public class E07_TestList {
    public static void main(String args[]) {
        IDClass[] idc = new IDClass[10];
        for (int i = 0; i < idc.length; i++)
            idc[i] = new IDClass();
        List<IDClass> lst = new ArrayList<IDClass>(
                Arrays.asList(idc));
        System.out.println("lst = " + lst);
        List<IDClass> subSet =
                lst.subList(lst.size() / 4, lst.size() / 2);
        System.out.println("subSet = " + subSet);
        // The semantics of the sub list become undefined if the
        // backing list is structurally modified!
        lst.removeAll(subSet);
        //subSet.clear();
        System.out.println("lst = " + lst);
    }
}