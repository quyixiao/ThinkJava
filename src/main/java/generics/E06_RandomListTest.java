//: generics/E06_RandomListTest.java
/****************** Exercise 6 *****************
 * Use RandomList with two more types in addition
 * to the one shown in main().
 ************************************************/
package generics;

import net.mindview.util.CountingGenerator;
import net.mindview.util.Generator;


/*****
 *
 *
 * 358页
 *  使用RandomList来处理两种额外的不同类型的元素，要区别于main()中己经用过的类型
 *
 *
 *
 * 1
 */
public class E06_RandomListTest {
    private static void dump(RandomList<?> rl) {
        for (int i = 0; i < 11; i++)
            System.out.print(rl.select() + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        RandomList<String> rs = new RandomList<String>();
        for (String s : ("The quick brown fox jumped over " +
                "the lazy brown dog").split(" "))
            rs.add(s);
        dump(rs);
        RandomList<Integer> ri = new RandomList<Integer>();
        Generator<Integer> gi =
                new CountingGenerator.Integer();
        for (int i = 0; i < 11; i++)
            ri.add(gi.next());
        dump(ri);
        RandomList<Character> rc = new RandomList<Character>();
        Generator<Character> gc =
                new CountingGenerator.Character();
        for (int i = 0; i < 11; i++)
            rc.add(gc.next());
        dump(rc);
    }
} 