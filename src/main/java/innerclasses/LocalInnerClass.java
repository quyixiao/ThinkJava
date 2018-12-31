package innerclasses;//: innerclasses/LocalInnerClass.java
// Holds a sequence of Objects.

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;


/***
 * 214页
 *
 *      局部内部类和匿名内部类的创建进行比较
 *
 *
 *
 *
 *      Counter返回的是序列中的下一个值，我们分别使用局部内部类和匿名内部类的实现了这个功能
 *  ，它们具有相同的行为和能力，既然局部内部类的名字在方法中是不可见的，那么为什么我们仍然使用
 *  局部内部类而不使用匿名内部类呢，唯一的理由是，我们需要一个己经命名的构造器，或者需要重载构造器，
 *  而匿名内部类只能助于实例初始化
 *      所以使用局部内部类而不使用匿名内部类的另一个理由是，需要不上一个该内部类的对象
 *
 *
 *      内部类的标识，
 *          外围类的名字，加上 "$",再加上内部类的名字，例如 ，LocalinnerClass.java生成的.class文件包括
 *
 *
 *
 * LocalInnerClass$LocalCount.class
 *
 *
 *
 */
interface Counter {
    int next();
}

public class LocalInnerClass {
    private int count = 0;

    Counter getCounter(final String name) {
        // A local inner class:
        class LocalCounter implements Counter {
            public LocalCounter() {
                // Local inner class can have a constructor
                print("LocalCounter()");
            }

            public int next() {
                printnb(name); // Access local final
                return count++;
            }
        }
        return new LocalCounter();
    }

    // The same thing with an anonymous inner class:
    Counter getCounter2(final String name) {
        return new Counter() {
            // Anonymous inner class cannot have a named
            // constructor, only an instance initializer:
            {
                print("Counter()");
            }

            public int next() {
                printnb(name); // Access local final
                return count++;
            }
        };
    }

    public static void main(String[] args) {
        LocalInnerClass lic = new LocalInnerClass();
        Counter
                c1 = lic.getCounter("Local inner "),
                c2 = lic.getCounter2("Anonymous inner ");
        for (int i = 0; i < 5; i++) {
            print(c1.next());
        }
        for (int i = 0; i < 5; i++) {
            print(c2.next());
        }
    }
}




/* Output:
LocalCounter()
Counter()
Local inner 0
Local inner 1
Local inner 2
Local inner 3
Local inner 4
Anonymous inner 5
Anonymous inner 6
Anonymous inner 7
Anonymous inner 8
Anonymous inner 9
*///:~
