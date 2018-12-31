package innerclasses;

import innerclasses.exercise6.SimpleInterface;


/*******
 *  196页
 *  创建一个至少有一个方法的接口，在某个方法内定义一个内部类以实现此接口，
 *  这个方法返回对此接口的引用
 *
 *
 *
 * 1
 */
public class E09_InnerClassInMethod {
    public SimpleInterface get() {
        class SI implements SimpleInterface {
            public void f() {
                System.out.println("SI.f");
            }
        }
        return new SI();
    }

    public static void main(String args[]) {
        SimpleInterface si =
                new E09_InnerClassInMethod().get();
        si.f();
    }
}