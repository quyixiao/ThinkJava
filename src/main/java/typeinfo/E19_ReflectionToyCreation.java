//: typeinfo/E19_ReflectionToyCreation.java
/****************** Exercise 19 *****************
 * In ToyTest.java, use reflection to create a
 * Toy object using the nondefault constructor.
 ***********************************************/
package typeinfo;


import java.lang.reflect.Constructor;


/******
 *
 *
 *
 * 337页
 *  在ToyTest.java中，使用反射机制，通过非默认的构造器创建Toy对象
 *
 *
 *
 * 1
 */
class SuperToy extends FancierToy {
    int IQ;

    public SuperToy(int intelligence) {
        IQ = intelligence;
    }

    public String toString() {
        return "I'm a SuperToy. I'm smarter than you.";
    }
}

public class E19_ReflectionToyCreation {
    public static Toy makeToy(String toyName, int IQ) {
        try {
            Class<?> tClass = Class.forName(toyName);
            for (Constructor<?> ctor : tClass.getConstructors()) {
                // Look for a constructor with a single parameter:
                Class<?>[] params = ctor.getParameterTypes();
                if (params.length == 1)
                    if (params[0] == int.class)
                        return (Toy) ctor.newInstance(
                                new Object[]{Integer.valueOf(IQ)});
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void main(String args[]) {
        System.out.println(makeToy("typeinfo.SuperToy", 150));
    }
}