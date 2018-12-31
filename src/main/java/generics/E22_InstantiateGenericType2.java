//: generics/E22_InstantiateGenericType2.java
/****************** Exercise 22 *****************
 * Use a type tag along with reflection to create
 * a method that uses the argument version of
 * newInstance() to create an object of a class
 * with a constructor that has arguments.
 ************************************************/
package generics;

import java.lang.reflect.Constructor;

import static net.mindview.util.Print.print;


/*****
 *
 * 383页
 *  使用类型标签与反射方法来创建一个反射方法，它将使用NewInstance()的参数版本来
 *  创建某个类对象，这个类拥有参数构造器
 *
 * @param <T>
 *
 *
 *     1
 */
class ClassAsFactory1<T> {
    Class<T> kind;

    public ClassAsFactory1(Class<T> kind) {
        this.kind = kind;
    }

    public T create(int arg) {
        try {
            // Technique 1 (verbose)
            for (Constructor<?> ctor : kind.getConstructors()) {
                // Look for a constructor with a single parameter:
                Class<?>[] params = ctor.getParameterTypes();
                if (params.length == 1)
                    if (params[0] == int.class)
                        return kind.cast(ctor.newInstance(arg));
            }
            // Technique 2 (direct)
            // Constructor<T> ct = kind.getConstructor(int.class);
            // return ct.newInstance(arg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}

public class E22_InstantiateGenericType2 {
    public static void main(String[] args) {
        ClassAsFactory1<Employee> fe = new ClassAsFactory1<Employee>(Employee.class);
        Employee emp = fe.create(1);
        if (emp == null) {
            print("Employee cannot be instantiated!");
        }
        ClassAsFactory1<Integer> fi = new ClassAsFactory1<Integer>(Integer.class);
        Integer i = fi.create(0);
        if (i == null) {
            print("11111Integer cannot be instantiated!");
        }
        System.out.println(i);
    }
}