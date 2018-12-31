//: interfaces/E13_Diamond.java
/****************** Exercise 13 ******************
 * Create an interface, inherit two new
 * interfaces from it, then multiply-inherit
 * a third interface from the second two.
 ***********************************************/
package interfaces;


/****
 * 180页
 * 创建一个接口，并从该接口继承两个接口，然后从后面两个接口多生继承第三个接口
 *
 */
interface BaseInterface {
    void f();
}

interface IntermediateInterface1 extends BaseInterface {
    void f();
}

interface IntermediateInterface2 extends BaseInterface {
    void f();
}

interface CombinedInterface
        extends IntermediateInterface1, IntermediateInterface2 {
    void f();
}

class CombinedImpl implements CombinedInterface {
    public void f() {
        System.out.println("CombinedImpl.f()");
    }
}

public class E13_Diamond {
    public static void main(String[] args) {
        new CombinedImpl().f();
    }
}