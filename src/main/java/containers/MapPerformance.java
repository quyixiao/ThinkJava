package containers;//: containers/MapPerformance.java
// Demonstrates performance differences in Maps.
// {Args: 100 5000} Small to keep build testing short

import java.util.*;


/**
 * 2018-07-17 除了IdentiyHashMap，所有的Map实现的插入操作都会随着Map的尺寸的变大面明显变慢
 * 但是，查找的代价通常比插入的小得多，这是一个好消息，因为执行查找元素的操作要比执行插入元素的
 * 多得多
 * Hashtable 的性能大体上与HashMap相当，因为HashMap是用来替代Hashtable的，因此它们使用了
 * 相同的底层和查找机制，这并没有什么令人吃惊的。
 * TreeMap通常比HashMap要慢，与使用TreeSet一样的，TreeMap是一种创建有序列表的方式，树的行为
 * ，总是保持有序，并且不必进行特殊的排序，一旦你填充了一个TreeMap,就可以调用KeySet 方法来攻取
 * 键的Set视图，然后调用toArray()来产生由这些键构成的数组，之后，你可以使用静态的方法Arrays.binarySerach()
 * 在排序数组快速查找对象，当然，这只有在HashMap的行为不可接受的情况下才有意义，因为HashMap本身就被设计
 * 为可以快速查找键，你还可以很方便的通过单个的对象创建操作，或者是调用putAll()，从TreeMap中创建HahsMap
 * 最后，当使用Map时，你的第一选择应该是HahsMap,只有在你要求Map始终保持有序时，才需要使用TreeMap
 * LinkedHashMap 在插入时比HashMap慢一点的，因为经维护散列数据结构同时不要维护链表，正是因为这种，使得
 * 基迭代速度更快
 * Hashmap的默认的负载因子是0.75 (只有当表达到四分之三满时，才进行散列，这个因子在时间各空间代价之间
 * 达到平衡，更高的负载因子)可以降你表所需的空间，但是增加查找代价，这很重要，因为查找是我们在大多数
 * 时间里所做的操作
 *
 */
public class MapPerformance {
    static List<Test<Map<Integer, Integer>>> tests =
            new ArrayList<Test<Map<Integer, Integer>>>();

    static {
        tests.add(new Test<Map<Integer, Integer>>("put") {
            int test(Map<Integer, Integer> map, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    map.clear();
                    for (int j = 0; j < size; j++)
                        map.put(j, j);
                }
                return loops * size;
            }
        });
        tests.add(new Test<Map<Integer, Integer>>("get") {
            int test(Map<Integer, Integer> map, TestParam tp) {
                int loops = tp.loops;
                int span = tp.size * 2;
                for (int i = 0; i < loops; i++)
                    for (int j = 0; j < span; j++)
                        map.get(j);
                return loops * span;
            }
        });
        tests.add(new Test<Map<Integer, Integer>>("iterate") {
            int test(Map<Integer, Integer> map, TestParam tp) {
                int loops = tp.loops * 10;
                for (int i = 0; i < loops; i++) {
                    Iterator it = map.entrySet().iterator();
                    while (it.hasNext())
                        it.next();
                }
                return loops * map.size();
            }
        });
    }

    public static void main(String[] args) {
        if (args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        Tester.run(new TreeMap<Integer, Integer>(), tests);
        Tester.run(new HashMap<Integer, Integer>(), tests);
        Tester.run(new LinkedHashMap<Integer, Integer>(), tests);
        Tester.run(
                new IdentityHashMap<Integer, Integer>(), tests);
        Tester.run(new WeakHashMap<Integer, Integer>(), tests);
        Tester.run(new Hashtable<Integer, Integer>(), tests);
    }
} /* Output: (Sample)
---------- TreeMap ----------
 size     put     get iterate
   10     748     168     100
  100     506     264      76
 1000     771     450      78
10000    2962     561      83
---------- HashMap ----------
 size     put     get iterate
   10     281      76      93
  100     179      70      73
 1000     267     102      72
10000    1305     265      97
------- LinkedHashMap -------
 size     put     get iterate
   10     354     100      72
  100     273      89      50
 1000     385     222      56
10000    2787     341      56
------ IdentityHashMap ------
 size     put     get iterate
   10     290     144     101
  100     204     287     132
 1000     508     336      77
10000     767     266      56
-------- WeakHashMap --------
 size     put     get iterate
   10     484     146     151
  100     292     126     117
 1000     411     136     152
10000    2165     138     555
--------- Hashtable ---------
 size     put     get iterate
   10     264     113     113
  100     181     105      76
 1000     260     201      80
10000    1245     134      77
*///:~
