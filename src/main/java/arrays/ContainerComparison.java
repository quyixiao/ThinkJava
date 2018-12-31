//: arrays/ContainerComparison.java
package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.mindview.util.Print.print;


/**
 *
 *
 * 434
 *
 *
 * 数组与其他的容器之间区别有三个方面：效率，类型和保存基本类型的能力，在java
 * 中，数组是一种效率最高的存储和随机访问对象，引用序列的方式，数组就是一个简单的
 * 线性序列，这使得元素访问非常快速，但是为了这种速度所付出的代价是数组对象的大小
 * 被固定，并且在其生命周期中不可改变，你可能会建议使用ArrayList 它可以通过创建
 * 一个新在其生命周期中不可改变，你可能会建议使用使用ArrayList ,它可以通过创建
 * 一个新实例，然后旧的实例中所有的引用移到新的实例中，从而实现更多的空间的自由
 * 分配，尽管通常应该首选ArrayList ，而不是数组，但是这种弹性需要开销，因此，AraayList
 * 的效率会比数组的效率会低很多
 *
 *
 *
 * 1
 */
class BerylliumSphere {
    private static long counter;
    private final long id = counter++;

    public String toString() {
        return "Sphere " + id;
    }
}

public class ContainerComparison {
    public static void main(String[] args) {
        BerylliumSphere[] spheres = new BerylliumSphere[10];
        for (int i = 0; i < 5; i++) {
            spheres[i] = new BerylliumSphere();
        }


        print(Arrays.toString(spheres));
        print(spheres[4]);

        List<BerylliumSphere> sphereList = new ArrayList<BerylliumSphere>();
        for (int i = 0; i < 5; i++)
            sphereList.add(new BerylliumSphere());
        print(sphereList);
        print(sphereList.get(4));

        int[] integers = {0, 1, 2, 3, 4, 5};
        print(Arrays.toString(integers));
        print(integers[4]);

        List<Integer> intList = new ArrayList<Integer>(
                Arrays.asList(0, 1, 2, 3, 4, 5));
        intList.add(97);
        print(intList);
        print(intList.get(4));
    }
}



/* Output:
[Sphere 0, Sphere 1, Sphere 2, Sphere 3, Sphere 4, null, null, null, null, null]
Sphere 4
[Sphere 5, Sphere 6, Sphere 7, Sphere 8, Sphere 9]
Sphere 9
[0, 1, 2, 3, 4, 5]
4
[0, 1, 2, 3, 4, 5, 97]
4
*///:~
