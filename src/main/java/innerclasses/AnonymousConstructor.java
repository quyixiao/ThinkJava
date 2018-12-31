package innerclasses;//: innerclasses/AnonymousConstructor.java
// Creating a constructor for an anonymous inner class.

import static net.mindview.util.Print.print;


/****
 *      在实例初始化操作的内部，可以看到有一段代码，它们不能作为字段初始化动作的一部分来
 * 执行，就是if语句，所以对于匿名类而言，实例初始化的实际效果就是构造器，当然它受到了聘用制，你
 * 不能重载实例初始化方法，所以你仅有一个这样的构造器
 *
 */
abstract class Base {
    public Base(int i) {
        print("Base constructor, i = " + i);
    }

    public abstract void f();
}

public class AnonymousConstructor {
    public static Base getBase(int i) {
        return new Base(i) {
            {
                print("Inside instance initializer");
            }

            public void f() {
                print("In anonymous f()");
            }
        };
    }

    public static void main(String[] args) {
        Base base = getBase(47);
        base.f();
    }
} /* Output:
Base constructor, i = 47
Inside instance initializer
In anonymous f()
*///:~
