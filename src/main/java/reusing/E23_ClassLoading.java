//: reusing/E23_ClassLoading.java
/****************** Exercise 23 ****************
 * Prove that class loading takes place only
 * once and may be caused by either the creation
 * of the first instance of that class or by
 * accessing a static member.
 ***********************************************/
package reusing;

/***
 *
 * 147页
 * 请证明加载类的动作仅发生一次，主题该类的第一个实例的创建或者对static
 * 成员的访问都有可能引起加载
 *
 */
class LoadTest {
    // The static clause is executed
    // upon class loading:
    static {
        System.out.println("Loading LoadTest");
    }

    static void staticMember() {
    }
}

public class E23_ClassLoading {
    public static void main(String args[]) {
        System.out.println("Calling static member");
        LoadTest.staticMember();
        System.out.println("Creating an object");
        new LoadTest();
    }
}