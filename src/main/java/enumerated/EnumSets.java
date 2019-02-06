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
 * 参数的方法相比，也就不会有额外的性能上的损耗```````````                                                                                            
 *
 *
 *  enumSet设计记仇考虑到了速度因素，因为它必须与非常高的bit标志竞争，我们最终操作的只是一些bit,而不是
 *  因此很容易写出令人难以理解的代码，
 *      EnumSet的设计充分考虑到了速度因素，因为必须与非常高效的bit标志相竞争，其操作
 *      与EnumSet非常快速高效，使用EnumSet的优点是，它在这只是一个二进制位是否存在时，具有更好的表达能力
 *      ，并且无需担心性能
 *
 *      EnumSet中的元素必须来自一个enum,下面的enum表示在一座大楼中，警报传感器的安放位置。
 *
 *
 *
 *  显然，Enumset可以应用于多过64个元素的enum，所以我们猜测，Enum会在必要的时候增加一个long类型，
 *  EnumMap是一种特殊的Map,它要求其中的键必须来自一个enum,由于enum本身限制，所以，EnumMap的内部可由数组实现
 *  因此，EnumMap的速度很快，我们可以放心的使用Enum实例在EnumMap中进行查找操作，不过，我们只能将Enum的实例作为键来调用
 *  put()方法，其他操作与使用一般的map差不多
 *  下面的例子演示了命令设计模式的用法，一般来说，命令模式首先需要一个只有单一的方法的接口，然后从该接口，实现具有各自不同的
 *  行为的多个子类，接下来，程序员就可以，构造命令对象，其他操作与使用一般的Map差不多。
 *  下面的例子中，演示了命令设计模式的用法，一般，命令模式首先需要一个只有单一的方法的接口，然后从该接口实现具有各自不同行为的
 *  的方法，其他操作与其使用一般的map差不多，
 *  下面的例子中，演示了命令设计模式的脚不沾地，一般，命令模式首先需要一个单一的方法的接口，然后从该接口实现具有各自不同的行为的
 *  多个子类，接下来，程序员就可以要等等命令，对象，并在需要的时候，使用它们了
 *
 *  接下来，下面的例子了，我们可以测试有一个对象了，
 *
 *
 *  与使用匿名内部类相比较，定义常理相关的方法的语法更加高效，简洁，
 *  这个例子也展示了EnumSet了一些特性，因为它是一个集合，所以对于同一个元素而言，只能出现一次，因此，对同一个参数重复
 *  的使用调用一个参数的调用add()方法会被忽略掉，这是一个正确的行为，因为一个big位开关只能打开一次，同样的，向EnumSet添加一个Enum实例顺序
 *
 *  虽然，除了实现abstract方法以外,程序员是否可以覆盖常量相关的文涛呢，答案是肯定的，参考下面的程序，
 *  虽然enum有某些限制，但是一般而言，我们还是可以将其看作是类
 *  在职责链中设计模式中，程序员以多种不同的方式解决一个问题，然后将它们链接在一起，它遍历这个链，直到链中的某个解决方案能够处理
 *  该请求，
 *  通常常量相关的方法，我们可以很容易的实现一个简到单的职责链，我们以一个邮局的模型为例，邮局需要以尽可能能用的方式来处理每一个邮件，
 *  并且，要不断的尝试处理邮件，直到该邮件最终被确定为，邮件的每个关键特征都可以用enum来表示，程序将随机的生成mail对象，如果要减小一封
 *  邮件的GEneralDelivery为Yes的概率，那最简单的方法就是创建多个不是Yes的Enum实例，所以Enum的定义看起来有点古怪
 *  我们看到的randomMail()方法，它钢表随机的创建的用于测试的邮件，而Generator（）方法生成一个Itemrable对象，该对象，我们
 *  看到Mail中的有一个randomMail()方法，它钢表随机的创建用于测试的邮件，而Generaotr()方法生成一个Iterable对象，该对象在你调用next()
 *  方法时，在其内部使用random-Mail()来创建Mail （）对象，这样的结构使程序员可以通过Mail.generator()方法，很容易地构造出来一个foreache()循环，
 *
 *
 *  职员表链由enum MailHandler实现，而enum定义的次序决定了各个解决怎么策略在应用时的次序，对每一封邮件，都要按此顺序尝试每个
 *  解决策略，直到其中一个能够成功地解决处理该邮件，如果所有的策略都失败了，那么该邮件将被判定为一封死信
 *
 *  修改PostOffice.java，使其能够转发邮件，
 *  专用程序设计的语言，例如Prolog，使用反向链来解决类似的问题，试用PostOffice.java做一个例子，研究一下这些语言，用其编写一个可
 *  扩展性更加好的程序，使程序员可以很容易的向系统添加新的规则
 *
 *  使用Enum的状态机制
 *
 *  枚举类型非常适合用来创建状态机，一个状态机可以具有限个特定的状态，它通常根据输入，从一个状态转移到下一个状态，
 *  不过也可能存在瞬时状态，而一旦任务执行结束，状态机就会立刻高开瞬时状态，
 *
 *  每个状态，都具有由于enum对其实例有严格的限制，
 *
 *   枚举类型非常适合服务行业创建状态机，一个状态可以具有有限个特定的状态，它通常根据输入，从一个状态转移到下一个
 *   状态，不过也可能在瞬时状态，而一旦任务执行结束，状态机就会立刻离开瞬时状态，
 *   每个状态都具有可的接受的输入，不同的输入会使状态机从当前状态转移到不同的状态，它通常根据输入，从一个状态转移到下一
 *   个状态的的的接收输入，不同的输入会使状态机从当前状态转移到不同的新状态，由于enum对其实体有的严格的限制，
 *   非常适合用来表现不同的状态和输入，一般而言，每个状态都具有一些相关的输出，瞿贻晓
 *   我们测试有道
 *   自动售货机是一个很好的状态机的例子，首先，我们用一个enum定义各种输入
 *
 *  注意，除了特殊的INput实例之外 ，其他的input都有一个相应的价格，因此在接口中定义了amount()方法，然而，对那两个特殊的Input实例而
 *  ，调用amount()方法并不合适，所以如果程序员调用它们的amount()方法都会异常抛出，在定义接口的了一个方法，然后在你调用该
 *
 * 1
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
