package reusing;


//: reusing/E13_InheritedOverloading.java

/****************** Exercise 13 *****************
 * Create a class with a method that is
 * overloaded three times. Inherit a new class,
 * add a new overloading of the method, and show
 * that all four methods are available in the
 * derived class.
 ***********************************************/

/***
 *  136页
 *  创建一个类，应带有一个被重载了三次的方法，继承产生了一个新的类，并添加
 *  一个该方法的新的重载定义，展示了四个方法在导出类中都是可以使用的
 *
 *
 * 1
 */
class ThreeOverloads {
    public void f(int i) {
        System.out.println("f(int i)");
    }

    public void f(char c) {
        System.out.println("f(char c)");
    }

    public void f(double d) {
        System.out.println("f(double d)");
    }
}

class MoreOverloads extends ThreeOverloads {
    public void f(String s) {
        System.out.println("f(String s)");
    }
}

public class E13_InheritedOverloading {
    public static void main(String args[]) {
        MoreOverloads mo = new MoreOverloads();
        mo.f(1);
        mo.f('c');
        mo.f(1.1);
        mo.f("Hello");
    }
}