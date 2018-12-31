package innerclasses;

import innerclasses.exercise6.SimpleInterface;


/*****
 *
 * 196页
 *  创建一个private内部类，让它实现一个public接口，写一个方法，它返回一个
 *  指向此private内部类实例的引用，并将此引用向上转型为该接口类型，通过尝试向下转型
 *  说明此内部类被完全隐藏了。
 *
 *
 *  1
 *
 */
class Outer5 {

    private class Inner implements SimpleInterface {
        public void f() {
            System.out.println("Outer5.Inner.f");
        }
    }

    public SimpleInterface get() {
        return new Inner();
    }

    public Inner get2() {
        return new Inner();
    }


}

public class E11_HiddenInnerClass {
    public static void main(String args[]) {
        Outer5 out = new Outer5();
        SimpleInterface si = out.get();
        si = out.get2();

        // Won't compile -- 'Inner' not visible:
        //Inner i1 = out.get2();
        //Inner i2 = (Inner)si;
    }
} 