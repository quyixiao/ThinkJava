//: enumerated/EnumMaps.java
// Basics of EnumMaps.
package enumerated;




import java.util.EnumMap;
import java.util.Map;




import static enumerated.AlarmPoints.*;
import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;


/**
 *
 * 602 页
 *
 *
 * EnumMap 是一种特殊的Map, 它要求其中的键（Key）必须来自一个enum，由于enum本身的限制，所以EnumMap在内部可由数组
 * 实现，因此EnumMap的速度很快，我们可以放心的使用EnumMap 中进行查找操作，不过，我们只能enum的实例作为键来调用put()
 * 方法，其他的操作与使用一般的Map差不多
 *
 *
 * 下面的例子中演示了命令设计模式的不同的用法，一般来说，命令设计模式首先需要一个只有单一的方法的接口，然后从该接口
 * 实现具有各自的行为的多个子类，接下来，程序员就可以构造命令对象，并在需要的时候使用它们。
 *
 *
 *
 *
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
