package containers;//: containers/Unsupported.java
// Unsupported operations in Java containers.

import java.util.*;


/**
 * 473
 * 因为Array.asList() 会生成一个List，它是基于一个固定大小的数组，你仅支持那些不会改变的数组
 * 大小的操作，对它而言是有道理的，任何会对底层数据结构的尺寸进行修改的方法都会产生一个
 * UnsupportOprationException异常，以表示支未获支持（一个编程错误）
 * <p>
 * 注意： 应该把Arrays.addAll()静态方法，这样可以生成允许使用所有的方法的普遍容器，这在main()
 * 方法，或Collection.addAll() 静态方法，这样可以生成允许使用所有的方法的普遍容器，这在main()
 * 中的第一个对test()的调用得到展示，这样的调用会产生新的尺寸可调用的底层数据结构，Collections类
 * 中的 不可修改 的方法将容器包装到了一个代理中，只要你执行行任何试图修改容器的操作都会，这个代理
 * 都会产生UnsupprotedOperationException 异常，使用这些方法的目的就是产生一个常量的容器对象，
 * 不可修改，的collecion方法完整列表将在稍后介绍
 * <p>
 * <p>
 * 1
 */
public class Unsupported {
    static void test(String msg, List<String> list) {
        System.out.println("--- " + msg + " ---");
        Collection<String> c = list;
        Collection<String> subList = list.subList(1, 8);
        // Copy of the sublist:
        Collection<String> c2 = new ArrayList<String>(subList);
        try {
            c.retainAll(c2);
        } catch (Exception e) {
            System.out.println("retainAll(): " + e);
        }
        try {
            c.removeAll(c2);
        } catch (Exception e) {
            System.out.println("removeAll(): " + e);
        }
        try {
            c.clear();
        } catch (Exception e) {
            System.out.println("clear(): " + e);
        }
        try {
            c.add("X");
        } catch (Exception e) {
            System.out.println("add(): " + e);
        }
        try {
            c.addAll(c2);
        } catch (Exception e) {
            System.out.println("addAll(): " + e);
        }
        try {
            c.remove("C");
        } catch (Exception e) {
            System.out.println("remove(): " + e);
        }
        // The List.set() method modifies the value but
        // doesn't change the size of the data structure:
        try {
            list.set(0, "X");
        } catch (Exception e) {
            System.out.println("List.set(): " + e);
        }
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("A B C D E F G H I J K L".split(" "));
        test("Modifiable Copy", new ArrayList<String>(list));


        System.out.println("========================111=========================");

        test("Arrays.asList()", list);
        System.out.println("========================222==========================");
        test("unmodifiableList()",
                Collections.unmodifiableList(
                        new ArrayList<String>(list)));
    }
}



/* Output:
--- Modifiable Copy ---
--- Arrays.asList() ---
retainAll(): java.lang.UnsupportedOperationException
removeAll(): java.lang.UnsupportedOperationException
clear(): java.lang.UnsupportedOperationException
add(): java.lang.UnsupportedOperationException
addAll(): java.lang.UnsupportedOperationException
remove(): java.lang.UnsupportedOperationException
--- unmodifiableList() ---
retainAll(): java.lang.UnsupportedOperationException
removeAll(): java.lang.UnsupportedOperationException
clear(): java.lang.UnsupportedOperationException
add(): java.lang.UnsupportedOperationException
addAll(): java.lang.UnsupportedOperationException
remove(): java.lang.UnsupportedOperationException
List.set(): java.lang.UnsupportedOperationException
*///:~
