//: containers/E03_VerifySet.java
/****************** Exercise 3 ******************
 * Using Countries, fill a Set with the same data
 * multiple times, then verify that the Set ends up
 * with only one of each instance. Try this with
 * HashSet, LinkedHashSet, and TreeSet.
 ************************************************/
package containers;

import java.util.LinkedHashSet;
import java.util.Set;

import static net.mindview.util.Countries.names;


/****
 *
 *
 * 470页牛逼
 *
 *
 * 使用Countries.用同样的数据多次填充Set,然后验证此Set没有重复的元素，使用HashSet,LinedHashSet和TreeSet做测试比较
 *
 *
 *
 * 1
 *
 *
 */

public class E03_VerifySet {
    public static void main(String[] args) {
        Set<String> set = new LinkedHashSet<String>();
        for (int i = 0; i < 5; i++) {
            set.addAll(names(10));
        }
        System.out.println(set);
    }
}