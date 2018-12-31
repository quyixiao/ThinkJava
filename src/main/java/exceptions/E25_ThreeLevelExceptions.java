//: exceptions/E25_ThreeLevelExceptions.java
/****************** Exercise 25 ******************
 * Create a three-level hierarchy of exceptions.
 * Now create a base class A with a method that
 * throws an exception at the base of your
 * hierarchy. Inherit B from A and override the
 * method so it throws an exception at level two
 * of your hierarchy. Repeat by inheriting class
 * C from B. In main(), create a C and upcast it
 * to A, then call the method.
 ***********************************************/
package exceptions;


/********
 *
 * 276页
 *      建立一个三层异常继承体系，然后创建基类A,它的一个方法能抛出异常体系
 *  的基类异常，让B继承A,并且覆盖这个方法，让它抛出第二层的异常，让C继承B，再次覆盖
 *  这个方法，让它抛出第三层的异常，在main()里创建一个C类型的对象，把它向上转型为A，然后
 *  调用这个方法。
 *
 *
 *  1
 *
 */
class Level1Exception extends Exception {
}

class Level2Exception extends Level1Exception {
}

class Level3Exception extends Level2Exception {
}

class A {
    public void f() throws Level1Exception {
        throw new Level1Exception();
    }
}

class B extends A {
    public void f() throws Level2Exception {
        throw new Level2Exception();
    }
}

class C extends B {
    public void f() throws Level3Exception {
        throw new Level3Exception();
    }
}

public class E25_ThreeLevelExceptions {
    public static void main(String args[]) {
        A a = new C();
        try {
            a.f();
        } catch (Level1Exception e) {
            System.out.println("Caught " + e);
        }
    }
}