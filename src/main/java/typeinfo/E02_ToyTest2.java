//: typeinfo/E02_ToyTest2.java
/********************** Exercise 2 *********************
 * Incorporate a new kind of interface into ToyTest.java
 * and verify that it is detected and displayed properly.
 ********************************************************/
package typeinfo;

import static net.mindview.util.Print.print;

/****
 * 318页
 *
 * 将新的inteface加到ToyTest.java中，看看这个程序是否能够正确检测出来并加以
 * 显示
 *
 *
 * 1
 *
 */

interface HasCPU {
}

class FancierToy extends FancyToy implements HasCPU {
}

public class E02_ToyTest2 {
    static void printInfo(Class<?> cc) {
        print("Class name: " + cc.getName() +
                " is interface? [" + cc.isInterface() + "]");
        print("Simple name: " + cc.getSimpleName());
        print("Canonical name : " + cc.getCanonicalName());
    }

    public static void main(String[] args) {
        Class<?> c = null;
        try {
            c = Class.forName("typeinfo.FancierToy");
        } catch (ClassNotFoundException e) {
            print("Can't find FancierToy");
            System.exit(1);
        }
        printInfo(c);
        for (Class<?> face : c.getInterfaces())
            printInfo(face);
        Class<?> up = c.getSuperclass();
        Object obj = null;
        try {
            obj = up.newInstance();
        } catch (InstantiationException e) {
            print("Cannot instantiate");
            System.exit(1);
        } catch (IllegalAccessException e) {
            print("Cannot access");
            System.exit(1);
        }
        printInfo(obj.getClass());
    }
} 