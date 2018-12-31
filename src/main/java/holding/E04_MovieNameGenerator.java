//: holding/E04_MovieNameGenerator.java
/****************** Exercise 4 ******************
 * Create a generator class that produces String objects
 * with the names of characters from your favorite
 * movie each time you call next(), and then loops
 * around to the beginning of the character list
 * when it runs out of names. Use this generator to
 * fill an array, an ArrayList, a LinkedList, a
 * HashSet, a LinkedHashSet, and a TreeSet, then
 * print each container.
 ***********************************************/
package holding;

import net.mindview.util.Generator;

import java.util.*;

import static net.mindview.util.Print.print;


/******
 *
 * 223页
 *      创建一个生成器类，它可以在每次调用其next()方法时，产生你最喜欢的电影
 *  （你可以使用Snow While或Star Wars）的字符成的名字（作为String对象），在字符列表中
 *  的电影名用完之后，循环到这个字符列表的开始处，使用这个生成器来填充数组，ArrayList，
 *  ListedList，HashSet，LinkedHashSet和Treeset，然后打印每一个容器
 *
 *  1
 *
 */
class MovieNameGenerator implements Generator<String> {
    String[] characters = {
            "Grumpy", "Happy", "Sleepy", "Dopey", "Doc", "Sneezy",
            "Bashful", "Snow White", "Witch Queen", "Prince"
    };
    int next;

    public String next() {
        String r = characters[next];
        next = (next + 1) % characters.length;
        return r;
    }
}

public class E04_MovieNameGenerator {
    private static final MovieNameGenerator mng =
            new MovieNameGenerator();

    static String[] fill(String[] array) {
        for (int i = 0; i < array.length; i++)
            array[i] = mng.next();
        return array;
    }

    static Collection<String>
    fill(Collection<String> collection) {
        for (int i = 0; i < 5; i++)
            collection.add(mng.next());
        return collection;
    }

    public static void main(String[] args) {
        print(Arrays.toString(fill(new String[5])));
        print(fill(new ArrayList<String>()));
        print(fill(new LinkedList<String>()));
        print(fill(new HashSet<String>()));
        print(fill(new LinkedHashSet<String>()));
        print(fill(new TreeSet<String>()));
    }
}