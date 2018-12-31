package containers;//: containers/AssociativeArray.java
// Associates keys with values.

import static net.mindview.util.Print.print;


/**
 * 正如你在第11章中所学到的，映射表（也称为关联数组）的基本思想就是它维护的是键-值
 * 对 关联，因此你可以使用键来找值，标准的java类库中包含了Map的几种基本实现，包括
 * HashMap,TreeMap,LinkedHashMap,WeakHashMap,ConcurrentHashMap,IdentityHashMap
 * ，它们都有同样的的基本接口Map,但是行为特性各不相同，这表现在效率，键值对的保存及
 * 呈现次序，对象的保存周期，映射表如何在多线程中程序中如何工作和判定 "键" 等价的策略
 * 等方面，Map接口实现的数量应该可以让你感觉到这种工具的重要性
 *
 *
 *
 * HashMap* Map 基于散列的实现（它取代了Hashtable） ，插入和查询的"键值对"的开销是固定的
 * 可以通过构造器设置容量和负载因子，以调整容器的性能
 *
 *
 * LinkedHashMap 类似于HashMap ,但是迭代遍历它时，取得键值对的顺序就是插入排序，或者是最近
 * 最少使用LRU 的次序，只比HashMap慢一点，而在迭代访问时反而更快，因为它使用链表维护内部的次序
 *
 *
 *
 *
 * TreeMap 基于红黑树的实现，查看 "键" 或 "键值对的" 时，它们会被排序（次序由Compareable 或Comparator 决定）
 * TreeMap的特点在于，所得到的结果经过排序的，TreeMap是唯一的带有subMap方法的Map，它可以返回一个子树
 *
 *
 *
 * WeakHashMap  弱键 ，映射允许释放映射所指向的对象，这是为解决某个类特殊问题而设定的，如果映射之外没有引用指
 * 向某类特殊问题而设计的，如果映射之外没有引用指向某个键 则此 键 可以被垃圾收集器回收
 *
 *
 *
 * ConcurrentHashMap 一种线程安全的Map,它不涉及同步加锁，我们将在"并发" 一章中讨论 ，
 *
 * IdentityHashMap  使用==代替equals() 对 键 进行 比较的散列映射 ，专为解决特殊的问题而设计的
 *
 *
 *
 * @param <K>
 * @param <V>
 */
public class AssociativeArray<K, V> {
    private Object[][] pairs;
    private int index;

    public AssociativeArray(int length) {
        pairs = new Object[length][2];
    }

    public void put(K key, V value) {
        if (index >= pairs.length)
            throw new ArrayIndexOutOfBoundsException();
        pairs[index++] = new Object[]{key, value};
    }

    @SuppressWarnings("unchecked")
    public V get(K key) {
        for (int i = 0; i < index; i++)
            if (key.equals(pairs[i][0]))
                return (V) pairs[i][1];
        return null; // Did not find key
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < index; i++) {
            result.append(pairs[i][0].toString());
            result.append(" : ");
            result.append(pairs[i][1].toString());
            if (i < index - 1)
                result.append("\n");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        AssociativeArray<String, String> map =
                new AssociativeArray<String, String>(6);
        map.put("sky", "blue");
        map.put("grass", "green");
        map.put("ocean", "dancing");
        map.put("tree", "tall");
        map.put("earth", "brown");
        map.put("sun", "warm");
        try {
            map.put("extra", "object"); // Past the end
        } catch (ArrayIndexOutOfBoundsException e) {
            print("Too many objects!");
        }
        print(map);
        print(map.get("ocean"));
    }
} /* Output:
Too many objects!
sky : blue
grass : green
ocean : dancing
tree : tall
earth : brown
sun : warm
dancing
*///:~
