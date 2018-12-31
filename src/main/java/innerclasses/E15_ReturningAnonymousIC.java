package innerclasses;


/***
 *
 *
 *  * 199页
 * 创建一个类，它有非默认的构造器，即需要参数的构造器，创建第二个类，它包含一个方法，能够返回对
 * 第一个类的对象的引用，通过定一个继承自第一个类的匿名内部类，来创建一个返回对象
 *
 * 1
 */
class NoDefault {
    private int i;

    public NoDefault(int i) {
        this.i = i;
    }

    public void f() {
        System.out.println("NoDefault.f");
    }
}

class Second {
    public NoDefault get1(int i) {
        // Doesn't override any methods:
        return new NoDefault(i) {
        };
    }

    public NoDefault get2(int i) {
        // Overrides f():
        return new NoDefault(i) {
            public void f() {
                System.out.println("Second.get2.f");
            }
        };
    }
}

public class E15_ReturningAnonymousIC {
    public static void main(String args[]) {
        Second sec = new Second();
        NoDefault nd = sec.get1(47);
        nd.f();
        nd = sec.get2(99);
        nd.f();
    }
}