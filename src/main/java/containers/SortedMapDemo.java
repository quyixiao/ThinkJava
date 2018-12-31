package containers;//: containers/SortedMapDemo.java
// What you can do with a TreeMap.

import net.mindview.util.CountingMapData;

import java.util.Iterator;
import java.util.TreeMap;

import static net.mindview.util.Print.print;


/**
 * 此处，键值对是按照的次序排序的，TreeMap中的次序是有序的，因此"位置"的概念才有意义，所以才能取得第一个和
 * 最后一个，所以才能取得第一个和最后一个元素，并且可以提取Map的子集
 *
 */
public class SortedMapDemo {
    public static void main(String[] args) {
        TreeMap<Integer, String> sortedMap =
                new TreeMap<Integer, String>(new CountingMapData(10));
        print(sortedMap);
        Integer low = sortedMap.firstKey();
        Integer high = sortedMap.lastKey();
        print(low);
        print(high);
        Iterator<Integer> it = sortedMap.keySet().iterator();
        for (int i = 0; i <= 6; i++) {
            if (i == 3) low = it.next();
            if (i == 6) high = it.next();
            else it.next();
        }
        print(low);
        print(high);
        print(sortedMap.subMap(low, high));
        print(sortedMap.headMap(high));
        print(sortedMap.tailMap(low));
    }
} /* Output:
{0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 6=G0, 7=H0, 8=I0, 9=J0}
0
9
3
7
{3=D0, 4=E0, 5=F0, 6=G0}
{0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 6=G0}
{3=D0, 4=E0, 5=F0, 6=G0, 7=H0, 8=I0, 9=J0}
*///:~
