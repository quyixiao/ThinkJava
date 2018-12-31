//: typeinfo/E09_GetDeclaredFields.java
// {Args: typeinfo.Derived}
/****************** Exercise 9 ******************
 * Modify the previous exercise so that it uses
 * Class.getDeclaredFields() to also display
 * information about the fields in a class.
 ***********************************************/
package typeinfo;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import static net.mindview.util.Print.print;


/******
 *
 *
 *
 * 318页
 *  修改前一个练习，让这个方法使用class.getDeclaredFiedlds()来打印一个类中的域的相关的信息
 *
 *
 *
 */
interface Iface {
    int i = 47;

    void f();
}

class Base1 implements Iface {
    String s;
    double d;

    public void f() {
        System.out.println("Base.f");
    }
}

class Composed {
    Base b;
}

class Derived1 extends Base1 {
    Composed c;
    String s;
}

public class E09_GetDeclaredFields {
    static Set<Class<?>> alreadyDisplayed =
            new HashSet<Class<?>>();

    static void printClasses(Class<?> c) {
        // getSuperclass() returns null on Object:
        if (c == null) return;
        print(c.getName());
        Field[] fields = c.getDeclaredFields();
        if (fields.length != 0)
            print("Fields:");
        for (Field fld : fields) {
            print("   " + fld);
        }
        for (Field fld : fields) {
            Class<?> k = fld.getType();
            if (!alreadyDisplayed.contains(k)) {
                printClasses(k);
                alreadyDisplayed.add(k);
            }
        }
        // Produces the interfaces that this class
        // implements:
        for (Class<?> k : c.getInterfaces()) {
            print("Interface: " + k.getName());
            printClasses(k.getSuperclass());
        }
        printClasses(c.getSuperclass());
    }

    public static void main(String args[]) throws Exception {

        for (int i = 0; i < args.length; i++) {
            print("Displaying " + args[i]);
            printClasses(Class.forName(args[i]));
            if (i < args.length - 1)
                System.out.println("==================");
        }
    }
}