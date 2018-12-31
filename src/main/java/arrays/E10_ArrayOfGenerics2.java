//: arrays/E10_ArrayOfGenerics2.java
/****************** Exercise 10 *****************
 * Modify ArrayOfGenerics.java to use containers
 * instead of arrays. Show that you can eliminate
 * the compile-time warnings.
 ************************************************/
package arrays;

import java.util.ArrayList;
import java.util.List;


/****
 * 442
 *   修改ArrayOfGenerics.java，在其中使用容器而不是数组，展示你可以根除编译期警告信息
 *
 */
public class E10_ArrayOfGenerics2 {
    public static void main(String[] args) {
        ArrayList<List<String>> ls =
                new ArrayList<List<String>>();
        ls.add(new ArrayList<String>());
        // Compile-time checking produces an error:
        //! ls.add(new ArrayList<Integer>());
        ls.get(0).add("Array of Generics");
        System.out.println(ls.toString());
    }
}