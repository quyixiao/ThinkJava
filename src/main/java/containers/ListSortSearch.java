package containers;//: containers/ListSortSearch.java
// Sorting and searching Lists with Collections utilities.

import java.util.*;

import static net.mindview.util.Print.print;


/**
 * 514 页
 *
 *
 * 1
 */
public class ListSortSearch {
    public static void main(String[] args) {
        List<String> list =
                new ArrayList<String>(Utilities.list);
        list.addAll(Utilities.list);
        print(list);

        System.out.println("===========================================");
        Collections.shuffle(list, new Random(47));
        print("Shuffled: " + list);
        System.out.println("------------------------------------------------");
        // Use a ListIterator to trim off the last elements:
        ListIterator<String> it = list.listIterator(10);
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        print("oooooooooTrimmed: " + list);
        Collections.sort(list);
        print("111111111111111111Sorted: " + list);
        String key = list.get(7);
        int index = Collections.binarySearch(list, key);
        print("Location of " + key + " is " + index +
                ", list.get(" + index + ") = " + list.get(index));
        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
        print("Case-insensitive sorted: " + list);
        key = list.get(7);
        index = Collections.binarySearch(list, key, String.CASE_INSENSITIVE_ORDER);
        print("Location of " + key + " is " + index +
                ", list.get(" + index + ") = " + list.get(index));
    }
} /* Output:
[one, Two, three, Four, five, six, one, one, Two, three, Four, five, six, one]
Shuffled: [Four, five, one, one, Two, six, six, three, three, five, Four, Two, one, one]
Trimmed: [Four, five, one, one, Two, six, six, three, three, five]
Sorted: [Four, Two, five, five, one, one, six, six, three, three]
Location of six is 7, list.get(7) = six
Case-insensitive sorted: [five, five, Four, one, one, six, six, three, three, Two]
Location of three is 7, list.get(7) = three
*///:~
