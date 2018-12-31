package innerclasses;


/*****
 *
 *
 * 205页
 * 创建一个接口U,它包含三个方法，创建第一个类A,它包含一个方法，在此方法中
 * 通过创建一个匿名内部类，来生成指向U的引用，创建第二个类B,它包含一个由U构成
 * 的数组，B应该有几个方法，第一个方法可以接受对U的引用并存储到数组中，第二方法将数组
 * 的引用设为null,第三个方法遍历此数组，并在U中调用这些方法，在main()中，创建一组
 * A对象和一个B的对象，用那些A类对象所产生的U类类型的引用填充B对象的数组，使用B回调所
 * 在A的对象，再从B中移除某些U的引用
 *
 */
interface U {
    void f();

    void g();

    void h();
}

class AA {
    public U getU() {
        return new U() {

            public void f() {
                System.out.println("A.f");
            }

            public void g() {
                System.out.println("A.g");
            }

            public void h() {
                System.out.println("A.h");
            }
        };
    }
}

class BB {
    U[] ua;

    public BB(int size) {
        ua = new U[size];
    }

    public boolean add(U elem) {
        for (int i = 0; i < ua.length; i++) {
            if (ua[i] == null) {
                ua[i] = elem;
                return true;
            }
        }
        return false; // Couldn't find any space
    }

    public boolean setNull(int i) {
        if (i < 0 || i >= ua.length)
            return false; // Value out of bounds
        // (Normally throw an exception)
        ua[i] = null;
        return true;
    }

    public void callMethods() {
        for (int i = 0; i < ua.length; i++)
            if (ua[i] != null) {
                ua[i].f();
                ua[i].g();
                ua[i].h();
            }
    }
}

public class E23_UAB {
    public static void main(String args[]) {
        AA[] aa = {new AA(), new AA(), new AA()};
        BB b = new BB(3);
        for (int i = 0; i < aa.length; i++)
            b.add(aa[i].getU());
        b.callMethods();
        System.out.println("****");
        b.setNull(0);

        b.callMethods();
    }
}