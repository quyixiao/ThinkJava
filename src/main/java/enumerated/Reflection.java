package enumerated;//: enumerated/Reflection.java
// Analyzing enums using reflection.

import net.mindview.util.OSExecute;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

enum Explore {HERE, THERE}


/**
 * 答案是：values() 是由编译器添加的static方法，可以看出，在创建Explor的过程中，编译器
 * 不为其添加了valuesOf()方法，这可能有点令人迷惑，Enum类不是己经有valueOf()方法了吗
 * 不过Enum中的ValesOf（）方法需要两个参数，而这个新增的方法只需一个参数，由于这里使用的
 * set只存储方法的名字，而不考虑方法的签名，所以在调用Explore.removeAll(Enum)之后，就只
 * 剩下了，从最后的输出可以看到，编译器将Explore标记为final类，所以无法得到
 *
 *
 *  答案是： values() 是由编译器添加static 方法，可以看出，在创建Explore的过程中，编译器
 *  还为其添加了valuesOf()方法，这可能有点令人迷惑， Enum类不是己经有valueOf()方法了吗，不过
 *  Enum 中的valueOf() 方法需要两个参数，而这个新增的方法只需一个参数，由于这里使用的Set只存
 *  储方法的名字，而不是考虑方法的签名，所以在调用Explore.removeAll(Enum)之后，就只剩下了。
 *
 *
 *
 */
public class Reflection {
    public static Set<String> analyze(Class<?> enumClass) {
        print("----- Analyzing " + enumClass + " -----");
        print("Interfaces:");
        for (Type t : enumClass.getGenericInterfaces()) {
            print(t);
        }
        print("Base: " + enumClass.getSuperclass());
        print("Methods: ");
        Set<String> methods = new TreeSet<String>();
        for (Method m : enumClass.getMethods()) {
            methods.add(m.getName());
        }
        print(methods);
        return methods;
    }

    public static void main(String[] args) {
        Set<String> exploreMethods = analyze(Explore.class);
        Set<String> enumMethods = analyze(Enum.class);
        print("Explore.containsAll(Enum)? " +
                exploreMethods.containsAll(enumMethods));
        printnb("Explore.removeAll(Enum): ");
        exploreMethods.removeAll(enumMethods);
        print(exploreMethods);
        // Decompile the code for the enum:
        OSExecute.command("javap Explore");
    }
}




/* Output:
----- Analyzing class Explore -----
Interfaces:
Base: class java.lang.Enum
Methods:
[compareTo, equals, getClass, getDeclaringClass, hashCode, name, notify, notifyAll, ordinal, toString, valueOf, values, wait]
----- Analyzing class java.lang.Enum -----
Interfaces:
java.lang.Comparable<E>
interface java.io.Serializable
Base: class java.lang.Object
Methods:
[compareTo, equals, getClass, getDeclaringClass, hashCode, name, notify, notifyAll, ordinal, toString, valueOf, wait]
Explore.containsAll(Enum)? true
Explore.removeAll(Enum): [values]
Compiled from "Reflection.java"
final class Explore extends java.lang.Enum{
    public static final Explore HERE;
    public static final Explore THERE;
    public static final Explore[] values();
    public static Explore valueOf(java.lang.String);
    static {};
}
*///:~
