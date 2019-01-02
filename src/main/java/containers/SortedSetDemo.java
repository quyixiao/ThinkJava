package containers;//: containers/SortedSetDemo.java
// What you can do with a TreeSet.

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import static net.mindview.util.Print.print;


/**
 *
 *
 * 480
 * sortSet 的意思是 "按照对象的比较函数对元素排序" 而不是指元素的插入的次序 ，插入的顺序可以用LinkedHashSet来保存的
 *
 *
 * 1
 */
public class SortedSetDemo {
    public static void main(String[] args) {
        SortedSet<String> sortedSet = new TreeSet<String>();
        Collections.addAll(sortedSet, "one two three four five six seven eight"
                        .split(" "));
        print(sortedSet);



        String low = sortedSet.first();
        String high = sortedSet.last();



        print(low);
        print(high);
        Iterator<String> it = sortedSet.iterator();
        for (int i = 0; i <= 6; i++) {
            if (i == 3) {
                low = it.next();
                System.out.println("-----low-----" + low);
            }

            if (i == 6) {
                high = it.next();
                System.out.println("----------high -------" + high);
            } else it.next();
        }
        print(low);
        print(high);
        print(sortedSet.subSet(low, high));
        print(sortedSet.headSet(high));
        print(sortedSet.tailSet(low));
    }
}



/* Output:
[eight, five, four, one, seven, six, three, two]
eight
two
one
two
[one, seven, six, three]
[eight, five, four, one, seven, six, three]
[one, seven, six, three, two]
*///:~
