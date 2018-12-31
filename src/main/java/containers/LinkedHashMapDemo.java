package containers;//: containers/LinkedHashMapDemo.java
// What you can do with a LinkedHashMap.

import net.mindview.util.CountingMapData;

import java.util.LinkedHashMap;

import static net.mindview.util.Print.print;

/**
 * 在输出中可以看到，键值对是以插入的顺序进行遍历的，甚至LRU算法的版本也是如此，
 * 但是，在LRU版本中，在只，访问过前面的六个元素移到了队列前面，然后再一次访问
 * 元素  o 时，它就被移到队列的后端了
 *
 *
 *
 *
 *
 */
public class LinkedHashMapDemo {
    public static void main(String[] args) {
        LinkedHashMap<Integer, String> linkedMap =
                new LinkedHashMap<Integer, String>(
                        new CountingMapData(9));
        print(linkedMap);
        // Least-recently-used order:
        linkedMap =
                new LinkedHashMap<Integer, String>(16, 0.75f, true);
        linkedMap.putAll(new CountingMapData(9));
        print(linkedMap);
        for (int i = 0; i < 6; i++) // Cause accesses:
            linkedMap.get(i);
        print(linkedMap);
        linkedMap.get(0);
        print(linkedMap);
    }
} /* Output:
{0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 6=G0, 7=H0, 8=I0}
{0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 6=G0, 7=H0, 8=I0}
{6=G0, 7=H0, 8=I0, 0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0}
{6=G0, 7=H0, 8=I0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 0=A0}
*///:~
