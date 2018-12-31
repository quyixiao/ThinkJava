//: generics/E17_Sets2.java
/****************** Exercise 17 *****************
 * Study the JDK documentation for EnumSet. You'll
 * see there's a clone() method.
 * However, you cannot clone() from the reference
 * to the Set interface passed in Sets.java. Can you
 * modify Sets.java to handle both the general case
 * of a Set interface as shown, and the special case
 * of an EnumSet, using clone() instead of creating
 * a new HashSet?
 ************************************************/
package generics;

import generics.watercolors.*;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import static generics.watercolors.Watercolors.*;
import static net.mindview.util.Print.print;


/*****
 *
 * 369页
 * 研究JDK文档中的有关EnumSet的部分，你会看到它定义了colne()方法，然而，在Sets.java中，你却不能复制Set
 * 接口中的引用，请试着修改Sets.java，使其不但能接受一般的Set接口，而且能直接接受EnumSet，并使用clone()而不是创建新的
 * Hashset对象
 *
 *
 *
 * 1
 */
class Sets2 {
    @SuppressWarnings("unchecked")
    protected static <T> Set<T> copy(Set<T> s) {
        if (s instanceof EnumSet)
            return ((EnumSet) s).clone();
        return new HashSet<T>(s);
    }

    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        Set<T> result = copy(a);
        result.addAll(b);
        return result;
    }

    public static <T>
    Set<T> intersection(Set<T> a, Set<T> b) {
        Set<T> result = copy(a);
        result.retainAll(b);
        return result;
    }

    public static <T> Set<T>
    difference(Set<T> superset, Set<T> subset) {
        Set<T> result = copy(superset);
        result.removeAll(subset);
        return result;
    }

    public static <T> Set<T> complement(Set<T> a, Set<T> b) {
        return difference(union(a, b), intersection(a, b));
    }
}


/****
 * 369页
 *
 *
 * 1
 */
public class E17_Sets2 {
    public static void main(String[] args) {
        Set<Watercolors> set1 =
                EnumSet.range(BRILLIANT_RED, VIRIDIAN_HUE);
        Set<Watercolors> set2 =
                EnumSet.range(CERULEAN_BLUE_HUE, BURNT_UMBER);
        print("set1: " + set1);
        print("set2: " + set2);
        print("union(set1, set2): " + Sets2.union(set1, set2));
        print("union(set1, set2) type: " +
                Sets2.union(set1, set2).getClass().getSimpleName());
        Set<Integer> set3 = new HashSet<Integer>();
        set3.add(1);
        Set<Integer> set4 = new HashSet<Integer>();
        set4.add(2);
        print("set3: " + set3);
        print("set4: " + set4);
        print("union(set3, set4): " + Sets2.union(set3, set4));
        print("union(set3, set4) type: " +
                Sets2.union(set3, set4).getClass().getSimpleName());
    }
} 