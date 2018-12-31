//: typeinfo/E01_ToyTest.java
/********************** Exercise 1 *********************
 * In ToyTest.java, comment out Toy's default constructor
 * and explain what happens.
 *******************************************************/
package typeinfo;

import static net.mindview.util.Print.print;

// Copy-pasted because these interfaces are not public
interface HasBatteries {
}

interface Waterproof {
}

interface Shoots {
}

class Toy {
    //Toy() {}
    Toy(int i) {
    }
}

class FancyToy extends Toy
        implements HasBatteries, Waterproof, Shoots {
    FancyToy() {
        super(1);
    }
}


/******
 *
 *
 * 318页
 *  在ToyTest.java中，将Toy的默认构造器注释掉，并解释发生了现象
 *
 */
public class E01_ToyTest {
    static void printInfo(Class<?> cc) {
        print("Class name: " + cc.getName() +
                " is interface? [" + cc.isInterface() + "]");
        print("Simple name: " + cc.getSimpleName());
        print("Canonical name : " + cc.getCanonicalName());
    }

    public static void main(String[] args) {
        Class<?> c = null;
        try {
            c = Class.forName("typeinfo.FancyToy");
        } catch (ClassNotFoundException e) {
            print("Can't find FancyToy");
            return;
        }
        printInfo(c);
        for (Class<?> face : c.getInterfaces())
            printInfo(face);
        Class<?> up = c.getSuperclass();
        Object obj = null;
        try {
            // Requires default constructor:
            obj = up.newInstance();
        } catch (InstantiationException e) {
            print("Cannot instantiate");
            return;
        } catch (IllegalAccessException e) {
            print("Cannot access");
            return;
        }
        printInfo(obj.getClass());
    }
}