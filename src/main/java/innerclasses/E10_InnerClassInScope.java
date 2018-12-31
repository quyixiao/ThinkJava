package innerclasses;

import innerclasses.exercise6.SimpleInterface;


/*****
 * 196页
 *  重复前一个练习，但将内部类定义在某个方法的一个作用域内
 *
 *
 * 1
 */
public class E10_InnerClassInScope {
    public SimpleInterface get() {
        {
            class SI implements SimpleInterface {
                public void f() {
                    System.out.println("SI.f");
                }
            }
            return new SI();
        }
    }

    public static void main(String args[]) {
        SimpleInterface si =
                new E10_InnerClassInScope().get();
        si.f();
    }
}