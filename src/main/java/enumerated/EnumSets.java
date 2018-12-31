//: enumerated/EnumSets.java
// Operations on EnumSets
package enumerated;

import java.util.EnumSet;






import static enumerated.AlarmPoints.*;



import static net.mindview.util.Print.print;


/***
 *
 *
 *
 * 601 页
 *
 *
 *
 * Set 是一种集合，只能向其中添加不重复的对象，当然，enum也要求其成员都是唯一的，所以enum中，删除或添加
 * 元素，所以它只能算是不太有用的集合，java se5 引入EnumSet ,是为了通过enum创建一种替代品，以替代传统的
 * 基于int的位标志，这种标志可能用来表示某种"开关"信息，不过，使用这种标志，我们最终操作只是一些bit, 而不是
 * 这些big想要的概念，因此很容易写出令人难以理解的代码。
 * EnumSet 的设计充分考虑到了速度因素，因为它必须与非常高效的bit 标志相竞争，其他的操作和hashSet相比，非常的快
 * 就其它而言，它可能将一个long值作为bit向量，所以EnumSet非常快速的高效，使用Enumset的优点是，它在说明一个二进制
 * 位是否存在时，具有更好的表达能力，并且无需担心性能问题
 *
 *
 *
 *
 * 使用static import 可以简化enum常量的使用，EnumSet的方法的名字都相当的直观，你可以查阅JDK文档找到其详细的
 * 描述，如果仔细的研究jdk文档，你还会发现一个有趣的地方，of() 方法被重载了很多的次数，不但为可变数量参数进行
 * 重载，而且为接收2至5个显式的参数己经可以解决整个问题了，但是对比显式的参数，会有一点性能损失，采用现在的设计
 * 当你只使用2到5个参数调用of()方法时，你可以调用对应的重载过的方法，速度稍快一些，当你使用一个参数的或多个参数的
 * 时候，你调用的将是使用可变参数的of()方法，注意，如果你只使用一个参数，编译器并不会构造可变参数数组，所以与调用有一个
 * 参数的方法相比，也就不会有额外的性能上的损耗
 *
 *
 */
public class EnumSets {
    public static void main(String[] args) {
        EnumSet<AlarmPoints> points = EnumSet.noneOf(AlarmPoints.class); // Empty set
        points.add(BATHROOM);
        print(points);
        points.addAll(EnumSet.of(STAIR1, STAIR2, KITCHEN));
        print(points);
        points = EnumSet.allOf(AlarmPoints.class);
        points.removeAll(EnumSet.of(STAIR1, STAIR2, KITCHEN));
        print(points);
        points.removeAll(EnumSet.range(OFFICE1, OFFICE4));
        print(points);
        points = EnumSet.complementOf(points);
        print(points);
    }
} /* Output:
[BATHROOM]
[STAIR1, STAIR2, BATHROOM, KITCHEN]
[LOBBY, OFFICE1, OFFICE2, OFFICE3, OFFICE4, BATHROOM, UTILITY]
[LOBBY, BATHROOM, UTILITY]
[STAIR1, STAIR2, OFFICE1, OFFICE2, OFFICE3, OFFICE4, KITCHEN]
*///:~
