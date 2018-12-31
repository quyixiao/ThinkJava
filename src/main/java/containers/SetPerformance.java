package containers;//: containers/SetPerformance.java
// Demonstrates performance differences in Sets.
// {Args: 100 5000} Small to keep build testing short

import java.util.*;

/**
 * HashSEt的性能基本上总是比Treset好，特别是在添加和本义元素时，面这两个操作也是最重的操作，
 * TreeSet 存在的原因是它可以维持元素的排序状态，并且因为迭代是我们更有可能执行的操作
 * 所以，用TreeSet迭代一般比用HashSEt 要快。注意：对于插入操作，LinkeHashSet 比HashSet的代价
 * 更高，这是由维护链表所带来额外开销造成的。
 */
public class SetPerformance {
    static List<Test<Set<Integer>>> tests =
            new ArrayList<Test<Set<Integer>>>();

    static {
        tests.add(new Test<Set<Integer>>("add") {
            int test(Set<Integer> set, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    set.clear();
                    for (int j = 0; j < size; j++)
                        set.add(j);
                }
                return loops * size;
            }
        });
        tests.add(new Test<Set<Integer>>("contains") {
            int test(Set<Integer> set, TestParam tp) {
                int loops = tp.loops;
                int span = tp.size * 2;
                for (int i = 0; i < loops; i++)
                    for (int j = 0; j < span; j++)
                        set.contains(j);
                return loops * span;
            }
        });
        tests.add(new Test<Set<Integer>>("iterate") {
            int test(Set<Integer> set, TestParam tp) {
                int loops = tp.loops * 10;
                for (int i = 0; i < loops; i++) {
                    Iterator<Integer> it = set.iterator();
                    while (it.hasNext())
                        it.next();
                }
                return loops * set.size();
            }
        });
    }

    public static void main(String[] args) {
        if (args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        Tester.fieldWidth = 10;
        Tester.run(new TreeSet<Integer>(), tests);
        Tester.run(new HashSet<Integer>(), tests);
        Tester.run(new LinkedHashSet<Integer>(), tests);
    }
} /* Output: (Sample)
------------- TreeSet -------------
 size       add  contains   iterate
   10       746       173        89
  100       501       264        68
 1000       714       410        69
10000      1975       552        69
------------- HashSet -------------
 size       add  contains   iterate
   10       308        91        94
  100       178        75        73
 1000       216       110        72
10000       711       215       100
---------- LinkedHashSet ----------
 size       add  contains   iterate
   10       350        65        83
  100       270        74        55
 1000       303       111        54
10000      1615       256        58
*///:~
