//: polymorphism/PrivateOverride.java
// Trying to override a private method.
package polymorphism;

import static net.mindview.util.Print.print;


/*****
 *
 *
 * 156页
 *
 *      只有非private方法才可以被覆盖，但是还是需要密切注意覆盖private方法的现象
 *  这时虽然编译器不会报错，但是也不会按照我们所期望的来执行，确切地说，在导出类中，对于
 *  基类中的private方法，最好采用不同的名字。
 */
public class PrivateOverride {
    private void f() {
        print("private f()");
    }

    public static void main(String[] args) {
        PrivateOverride po = new Derived();
        po.f();
    }
}

class Derived extends PrivateOverride {
    public void f() {
        print("public f()");
    }
} /* Output:
private f()
*///:~
