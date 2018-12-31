package innerclasses;


/*****
 * 203页
 *  创建一个包含嵌套类的接口，该嵌套类中有一个static方法，它将调用接口的方法并显示结果，
 *  实现这个接口，并将这个实现的一个实例传递给这个方法
 *
 */
interface I {
    void f();

    void g();

    class Nested {
        static void call(I impl) {
            System.out.println("Calling I.f()");
            impl.f();
            System.out.println("Calling I.g()");
            impl.g();
        }
    }
}

public class E21_InterfaceWithNested2 {
    public static void main(String[] args) {
        I impl = new I() {
            public void f() {
            }

            public void g() {
            }
        };
        I.Nested.call(impl);
    }
}