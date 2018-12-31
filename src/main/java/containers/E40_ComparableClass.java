//: containers/E40_ComparableClass.java
/****************** Exercise 40 *****************
 * Create a class containing two String objects
 * and make it Comparable so that the comparison
 * only cares about the first String. Fill an array
 * and an ArrayList with objects of your class,
 * using the RandomGenerator generator. Demonstrate that
 * sorting works properly. Now make a Comparator that
 * only cares about the second String, and demonstrate
 * that sorting works properly. Also perform a binary
 * search using your Comparator.
 ***********************************************/
package containers;

import net.mindview.util.CollectionData;
import net.mindview.util.Generated;
import net.mindview.util.Generator;
import net.mindview.util.RandomGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static net.mindview.util.Print.print;

class TwoString implements Comparable<TwoString> {
    String s1, s2;

    public TwoString(String s1i, String s2i) {
        s1 = s1i;
        s2 = s2i;
    }

    public String toString() {
        return "[s1 = " + s1 + ", s2 = " + s2 + "]";
    }

    public int compareTo(TwoString rv) {
        String rvi = rv.s1;
        return s1.compareTo(rvi);
    }

    private static RandomGenerator.String gen =
            new RandomGenerator.String();

    public static Generator<TwoString> generator() {
        return new Generator<TwoString>() {
            public TwoString next() {
                return new TwoString(gen.next(), gen.next());
            }
        };
    }
}

class CompareSecond implements Comparator<TwoString> {
    public int compare(TwoString sc1, TwoString sc2) {
        return sc1.s2.compareTo(sc2.s2);
    }
}

public class E40_ComparableClass {
    public static void main(String[] args) {
        TwoString[] array = new TwoString[10];
        Generated.array(array, TwoString.generator());
        print("before sorting, array = " +
                Arrays.asList(array));
        Arrays.sort(array);
        print("\nafter sorting, array = " +
                Arrays.asList(array));
        Arrays.sort(array, new CompareSecond());
        print(
                "\nafter sorting with CompareSecond, array = " +
                        Arrays.asList(array));
        ArrayList<TwoString> list = new ArrayList<TwoString>(
                CollectionData.list(TwoString.generator(), 10));
        TwoString zeroth = list.get(0);
        print("\nbefore sorting, list = " + list);
        Collections.sort(list);
        print("\nafter sorting, list = " + list);
        Comparator<TwoString> comp = new CompareSecond();
        Collections.sort(list, comp);
        print(
                "\nafter sorting with CompareSecond, list = "
                        + list);
        int index =
                Collections.binarySearch(list, zeroth, comp);

        print("\nFormer zeroth element " +
                zeroth + " now located at " + index);
    }
}