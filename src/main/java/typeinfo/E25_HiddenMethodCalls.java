//: typeinfo/E25_HiddenMethodCalls.java
/****************** Exercise 25 *****************
 * Create a class containing private, protected and
 * package access methods. Write code to access these
 * methods from outside of the class’s package.
 ************************************************/
package typeinfo;

import typeinfo.classa.A;

import java.lang.reflect.Method;


/******
 *
 *
 * 350页
 *  创建一个包含
 *
 *
 *
 *  1
 */
public class E25_HiddenMethodCalls {
    static void callHiddenMethod(Object a, String methodName)
            throws Exception {
        Method g = a.getClass().getDeclaredMethod(methodName);
        g.setAccessible(true);
        g.invoke(a);
    }

    public static void main(String[] args) throws Exception {
        class B extends A{
            protected void b() {
                System.out.println("=============");
                super.b();
            }
        }
        A objA = new A();
        //objA.a();  Compile time error
        //! objA.b();  Compile time error
        //! objA.c();  Compile time error
        callHiddenMethod(objA, "a");
        callHiddenMethod(objA, "b");
        callHiddenMethod(objA, "c");
        B objB = new B();
        objB.b();
    }
}





/* Output:
         A.a()
         A.b()
         A.c()
         A.b()
         *///:~
//: typeinfo/classa/A.java
