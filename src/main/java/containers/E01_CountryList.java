//: containers/E01_CountryList.java
/****************** Exercise 1 ******************
 * Create a List (try both ArrayList and LinkedList)
 * and fill it using Countries. Sort the list and
 * print it, then apply Collections.shuffle() to the
 * list repeatedly, printing it each time so you
 * can see how the shuffle() method randomizes the
 * list differently each time.
 ************************************************/
package containers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static net.mindview.util.Countries.names;
import static net.mindview.util.Print.print;


/*****
 *
 *
 * 470页
 *  创建一个Lis（用ArrayList和LinkedList多尝试一下），然后用Contries来填充）
 *  对的列表排序并打印然后将Conlections.huffle()方法重复的应用于该列表，并且每次都打印它
 *  这样你就可以看到Shffele()方法是每次找列表随机打乱的了
 *
 *1
 *
 *
 *
 */
public class E01_CountryList {
    private static Random rnd = new Random(47);

    public static void main(String[] args) {
        List<String> l = new ArrayList<String>(names(8));
        Collections.sort(l);
        print("sorted: " + l);
        for (int i = 1; i < 5; i++) {
            Collections.shuffle(l, rnd);
            print("shuffled (" + i + ","+rnd+"): " + l);
        }
    }
}