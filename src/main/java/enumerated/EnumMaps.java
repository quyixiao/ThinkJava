//: enumerated/EnumMaps.java
// Basics of EnumMaps.
package enumerated;


import java.util.EnumMap;
import java.util.Map;

import static enumerated.AlarmPoints.*;
import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;


/**
 * 602 页
 * <p>
 * <p>
 * EnumMap 是一种特殊的Map, 它要求其中的键（Key）必须来自一个enum，由于enum本身的限制，所以EnumMap在内部可由数组
 * 实现，因此EnumMap的速度很快，我们可以放心的使用EnumMap 中进行查找操作，不过，我们只能enum的实例作为键来调用put()
 * 方法，其他的操作与使用一般的Map差不多
 * <p>
 * <p>
 * 下面的例子中演示了命令设计模式的不同的用法，一般来说，命令设计模式首先需要一个只有单一的方法的接口，然后从该接口
 * 实现具有各自的行为的多个子类，接下来，程序员就可以构造命令对象，并在需要的时候使用它们。
 *
 * 与EnumSet不相亲，enum实例定义时的次序决定了其在EnumMap中的顺序，
 * main（）方法的最后部分说明了，enum的每个实例为一个键，总是存在的，但是，如果你没有这个键调用put()方法来存入的相应的值
 * 的话，其对应的值就是null。
 * 与常理相关的文涛，相比，EnumMap有一个优点，那EnumMap允许程序员改变值对象，而常量相关的方法在编译期就被固定了
 * 稍后你会看到，在你的程序多种类型的enum，而且他们之间的存在互相操作的情况下，我们可以用EnumMap实现多路分发
 * 常理相关的方法
 * Java的Enum有一个非常有趣的我，那它欢迎垂询 程序员为Enum实例编写一个方法，从而为每个Enum实例中的各自不同的行为，要实现常理相关的相关的方法
 * 你需要为Enum定义一个多个abstract方法，然后为每个Enum实例实现该抽象方法，参考下面的例子，
 *
 * 通过相应的Enum实例，我们可以调用其上的方法，这通常也称为驱动的代码，请注意它与前面反映到的命令模式的相似之处
 *
 * 在面向对象的程序设计中，不同的行为与不同的类关联，而常理相关的方法，每个Enum实例可以具备自己独特的行为，这似乎说明了每个Enum实例像一个独特的类
 * 在上面的例子中，Enum实例似乎被当作其超类，ConstantSpecificMethod来使用，在调用getInfo()方法时，体现多个多态的行为。
 *
 * 然而，enum实例与类的相似之处也仅限于此，我们并不能真的将enum实例作为一个类型来使用
 *
 * 在使用f1()中，编译不允许我们将一个Enum实例当作Class类型，如果我们分析一下编译器生成代码，就知道这种行为是很正常的，
 * 我们就知道这样的行为是很正常的，因为每个Enum元素都是一个LikeClasses类型，static final实例。
 * 同时由于它们是static实例，无法访问外部类的非static元素或方法，所以对于内部的enum的实例而言，其行为与一般的内部类并不相同，
 * 再看一个更有趣的关于洗车的例子，每个顾客在洗车时，都有一个选择菜单，每个选择时应有一个不同的动作，可以将一个常量相关的方法关联到一个选择上
 * 再使用一个EnumSet来保存客户选择
 *
 *
 * 与常量相关的方法，constant-spccific methods 将在下一节中介绍，相比，EnumMap有一个优点，
 * 那EnumMap允许程序员改变值对象，而常量相关的方法在编译期就被固定了
 *
 * 稍后你会看到，在你有多种类型的enum，而且它们之间存在互操作的情况下，我们可以用EnumMap实现多路分发
 *
 *
 *
 * java的Enum有一个非常有趣的特性，那它鸡程序员为enum实例编写方法，从而为每个enum实例赋予各自不同的行为，
 * 要实现常量相关的方法，你需要为enum定义一个enum或多个abstract方法，然后为每个enum实例该抽象方法，
 * 参考下面的抽象方法，下面的例子
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * 1
 *
 */
interface Command {
    void action();
}

public class EnumMaps {
    public static void main(String[] args) {
        EnumMap<AlarmPoints, Command> em = new EnumMap<AlarmPoints, Command>(AlarmPoints.class);
        em.put(KITCHEN, new Command() {

            public void action() {
                print("Kitchen fire!");
            }
        });
        em.put(BATHROOM, new Command() {

            public void action() {
                print("Bathroom alert!");
            }
        });

        for (Map.Entry<AlarmPoints, Command> e : em.entrySet()) {
            printnb(e.getKey() + ": ");
            e.getValue().action();
        }

        try { // If there's no value for a particular key:
            em.get(UTILITY).action();
        } catch (Exception e) {
            print(e);
        }
    }
} /* Output:
BATHROOM: Bathroom alert!
KITCHEN: Kitchen fire!
java.lang.NullPointerException
*///:~
