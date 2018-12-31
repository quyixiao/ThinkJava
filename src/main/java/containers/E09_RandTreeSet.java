//: containers/E09_RandTreeSet.java
/****************** Exercise 9 *****************
 * Use RandomGenerator.String to fill a TreeSet,
 * but use alphabetic ordering. Print the TreeSet
 * to verify the sort order.
 ***********************************************/
package containers;

import net.mindview.util.CollectionData;
import net.mindview.util.RandomGenerator;

import java.util.TreeSet;


/****
 *
 * 480页
 *  使用RandomGenerator.String来填充TreeSet，但是要使用字母序，打印这个
 *  TreeSET，并验证其排序顺序
 *
 *
 *
 */
public class E09_RandTreeSet {
    public static void main(String args[]) {
        TreeSet<String> ts =

                new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        ts.addAll(CollectionData.list(
                new RandomGenerator.String(), 10));
        System.out.println("ts = " + ts);
    }
}