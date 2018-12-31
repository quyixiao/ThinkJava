package innerclasses;


/*****
 *
 * 195页
 *  创建一个含有private域和private方法方法的类，创建一个内部类，它有一个方法可用来修改外围类的域，并调用外围
 *  类的方法，在外围类的另一个方法中，创建此内部类的对象，
 *  并且调用它的方法，然后说明对外围类的对象的影响
 *
 *
 *  1
 *
 */
public class E07_InnerClassAccess {
    private int i = 10;

    private void f() {
        System.out.println("E07_InnerClassAccess.f");
    }

    class Inner {
        void g() {
            i++;
            f();
        }
    }

    public void h() {
        Inner in = new Inner();
        in.g();
        System.out.println("i = " + i);
    }

    public static void main(String args[]) {
        E07_InnerClassAccess ica = new E07_InnerClassAccess();
        ica.h();
    }
}