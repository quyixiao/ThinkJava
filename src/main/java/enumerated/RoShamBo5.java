//: enumerated/RoShamBo5.java
// Multiple dispatching using an EnumMap of EnumMaps.
package enumerated;

import java.util.EnumMap;

import static enumerated.Outcome.*;


/***
 *
 *
 * 619 页
 * 使用EnumMap能够实现，真正的 两路分发，EnumMap是为enum专门设计的一种性能非常好的特殊的Map,
 * 由于我们的上目的摸索出两种未知类型的，所以可以用一个DumMap的EnumMap 来实现两路分发
 *
 *  该程序在一个static子句中初始化实现两路分发的解决方案，我们注意到，每个enum实例都有一个
 *  固定的位置（基于其声明的次序），并且可能通过ordinal() 方法取得该值，因此我们可能使用二维
 *  数组，将竞争者映射到结果，采用这种方式能够获得最简洁的，最直接的方案，很可能也是最快速的，
 *  虽然我们知道EnumMap内部其实也是使用数组来实现的
 *
 *
 *
 * table 与前一个例子中的initRow() 方法的调用次序完表全相同
 *
 * 与前面一个例子相比，这个程序代码虽然简短，但表达能力却是更强，部分的原因是其代码更易于理解与修改
 * 而也是更直接，不过，由于它使用的是数组，所以这种方式不太安全，如果使用一个大型的数组，可能会不小心
 * 使用了错误尺寸，而且，如果你的测试不能覆盖所有的可能性，有些错误可能会从你眼前溜过。
 * 事实上的，以上的所有的解决方案只是各种不同的类型的表罢了，不过，分析各种表现形式，找出最适合的
 * 那一种，还是很有价值的，虽然上例是最简洁的一种解决方案，但它也是相当的僵硬的方案，因为它只能针对给定的
 * 常量输出，然而，也没有什么特别的理由阻止你用table来生成成功的对象，对于某类问题而言， "表驱动式编码" 的概念
 * 具有非常强大的功能。
 *
 *
 *
 *
 * 虽然枚举类型本身不是特别复杂，但我们是将本章安排在全书比较靠后的位置，这是因为程序可以将enum与java语言的其他的
 * 功能结合使用，例如 多态，泛型，反射
 *
 *
 * 虽然枚举类型比C或C++中的enum更加的成熟，但它仍然是一个小的功能，Java设计有它也己经，虽然有点笨拙，存在很多年
 * 了，正如我在本书中一再强调的那样，优雅个清晰很重要。正是它们区分了成功的解决方案和失败的解决方案，而失败的解决
 * 方案就是因为其他的人无法理解
 *
 *
 * 关于清晰的话题，java.1.0.对术语的enumration的选择正是一个不幸的反例，对于一个专门的用于从序列中选择每一个元素
 * 的对象而言，Java竟然没有使用更加通用的，更普遍的术语iterator来表示它，有些语言甚至将枚举的数据类型称为enumeration，java
 * 修正了这个错误，但是Eumeration接口已经无法轻易地抹去了，因此它将一直存在于旧的，甚至有些新的。代码类库以及文档中。
 *
 *
 *  该程序在一个static子句中初始化EnumMap 对象，具体见表格似的initRow()方法调用，请
 *  注意，compate()方法，你可以看到，在一行语句中发生了两次分发
 *
 *
 * 我们还可以进一步简化实现两路分发的解决方案，我们注意到，每个enum实例都有一个
 * 固定值，因此我们可以使用二维数组，将竞争者映射到竞争结果，采用这种方式能够获得
 * 最简洁的，最直接的解决方案，很可能也是最快速的，虽然，我们知道enumMap内部其实也是
 * 使用数组实现的
 *
 *
 *
 *
 *
 *
 */
enum RoShamBo5 implements Competitor<RoShamBo5> {
    PAPER, SCISSORS, ROCK;
    static EnumMap<RoShamBo5, EnumMap<RoShamBo5, Outcome>>
            table = new EnumMap<RoShamBo5,
            EnumMap<RoShamBo5, Outcome>>(RoShamBo5.class);

    static {
        for (RoShamBo5 it : RoShamBo5.values())
            table.put(it,
                    new EnumMap<RoShamBo5, Outcome>(RoShamBo5.class));
        initRow(PAPER, DRAW, LOSE, WIN);
        initRow(SCISSORS, WIN, DRAW, LOSE);
        initRow(ROCK, LOSE, WIN, DRAW);
    }

    static void initRow(RoShamBo5 it,
                        Outcome vPAPER, Outcome vSCISSORS, Outcome vROCK) {
        EnumMap<RoShamBo5, Outcome> row =
                RoShamBo5.table.get(it);
        row.put(RoShamBo5.PAPER, vPAPER);
        row.put(RoShamBo5.SCISSORS, vSCISSORS);
        row.put(RoShamBo5.ROCK, vROCK);
    }

    public Outcome compete(RoShamBo5 it) {
        return table.get(this).get(it);
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo5.class, 20);
    }
} /* Same output as RoShamBo2.java *///:~
