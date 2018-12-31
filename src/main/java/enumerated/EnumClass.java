package enumerated;//: enumerated/EnumClass.java
// Capabilities of the Enum class

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

enum Shrubbery {GROUND, CRAWLING, HANGING}


/**
 *  590页
 *
 *
 *  我们已经在第5章看到，调用enum的values()方法，可以遍历enum实例，values() 方法返回enum 实例数组，而且该
 *  数组中的数据元素保存其在enum中，声明时顺序，因此你可以在循环中使用values() 返回的数组
 *  创建enum中声明时的顺序，因此你可以在循环中使用values() 返回数组
 *
 *  创建enum时，编译器会为你生成一个相关的类，这个类继承自java.lang.Enum。下面的例子演示了Enum提供的一些功能
 *
 *
 * ordinal() 方法返回一个int值，这是每个enum实例在声明时的次序，从0开始，可以使用==来比较enum实例，编译器会自动的
 * 为你提供equals() 和hashCode() 方法，Enum类实现了Compareable接口，所以它具有CompareTo()方法，同时，它还实现了Serialiable
 * 接口，
 *
 * 如果在enum实例上调用getDeclaringClass() 方法，我们就能知道其所属的enum类
 *
 * name() 方法返回的enum实例声明时的名字，这与使用toString() 方法的效果相同，values() 是在Enums() 中定义的statics方法，它
 * 根据给定的名字返回相应的enum实例，如果不存在给定名字的实例，将会抛出异常
 *
 *
 *
 *
 *
 *
 *
 */
public class EnumClass {
    public static void main(String[] args) {
        for (Shrubbery s : Shrubbery.values()) {
            print(s + " ordinal: " + s.ordinal());
            printnb(s.compareTo(Shrubbery.CRAWLING) + " ");
            printnb(s.equals(Shrubbery.CRAWLING) + " ");
            print(s == Shrubbery.CRAWLING);
            print(s.getDeclaringClass());
            print(s.name());
            print("----------------------");
        }
        // Produce an enum value from a string name:
        for (String s : "HANGING CRAWLING GROUND".split(" ")) {
            Shrubbery shrub = Enum.valueOf(Shrubbery.class, s);
            print(shrub);
        }
    }
} /* Output:
GROUND ordinal: 0
-1 false false
class Shrubbery
GROUND
----------------------
CRAWLING ordinal: 1
0 true true
class Shrubbery
CRAWLING
----------------------
HANGING ordinal: 2
1 false false
class Shrubbery
HANGING
----------------------
HANGING
CRAWLING
GROUND
*///:~
