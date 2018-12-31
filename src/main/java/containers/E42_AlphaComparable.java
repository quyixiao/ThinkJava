//: containers/E42_AlphaComparable.java
/****************** Exercise 42 *****************
 * Modify Exercise 40 so that an alphabetic sort
 * is used.
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

class TwoStringAlphabetic
        implements Comparable<TwoStringAlphabetic> {
    String s1, s2;

    public TwoStringAlphabetic(String s1i, String s2i) {
        s1 = s1i;
        s2 = s2i;
    }

    public String toString() {
        return "[s1 = " + s1 + ", s2 = " + s2 + "]";
    }

    public int compareTo(TwoStringAlphabetic rv) {
        String rvi = rv.s1;
        return s1.toLowerCase().compareTo(rvi.toLowerCase());
    }

    private static RandomGenerator.String gen =
            new RandomGenerator.String();

    public static Generator<TwoStringAlphabetic> generator() {
        return new Generator<TwoStringAlphabetic>() {
            public TwoStringAlphabetic next() {
                return new TwoStringAlphabetic(
                        gen.next(), gen.next());
            }
        };
    }
}

class CompareSecondAlphabetic
        implements Comparator<TwoStringAlphabetic> {
    public int compare(TwoStringAlphabetic sc1,
                       TwoStringAlphabetic sc2) {
        return sc1.s1.toLowerCase().compareTo(
                sc2.s1.toLowerCase());
    }
}

public class E42_AlphaComparable {
    public static void main(String[] args) {
        TwoStringAlphabetic[] array =
                new TwoStringAlphabetic[10];
        Generated.array(array, TwoStringAlphabetic.generator());
        print("before sorting, array = " +
                Arrays.asList(array));
        Arrays.sort(array);
        print("\nafter sorting, array = " +
                Arrays.asList(array));
        Arrays.sort(array, new CompareSecondAlphabetic());
        print("\nafter sorting with CompareSecondAlphabetic," +
                " array = " +
                Arrays.asList(array));
        ArrayList<TwoStringAlphabetic> list =
                new ArrayList<TwoStringAlphabetic>(
                        CollectionData.list(
                                TwoStringAlphabetic.generator(), 10));
        TwoStringAlphabetic zeroth = list.get(0);
        print("\nbefore sorting, list = " + list);
        Collections.sort(list);
        print("\nafter sorting, list = " + list);
        Comparator<TwoStringAlphabetic> comp =
                new CompareSecondAlphabetic();
        Collections.sort(list, comp);
        print("\nafter sorting with CompareSecondAlphabetic," +
                " list = "
                + list);
        int index =
                Collections.binarySearch(list, zeroth, comp);
        print("\nFormer zeroth element " +
                zeroth + " now located at " + index);
    }
} 