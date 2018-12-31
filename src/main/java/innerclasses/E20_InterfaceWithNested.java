package innerclasses;


/*****
 * 203页
 * 创建一个包含嵌套类的接口，实现此接口并创建嵌套类的实例
 *
 */
interface WithNested {
    class Nested {
        int i;

        public Nested(int i) {
            this.i = i;
        }

        void f() {
            System.out.println("Nested.f");
        }
    }
}

class B2 implements WithNested {
}

public class E20_InterfaceWithNested {
    public static void main(String args[]) {
        B2 b = new B2();
        WithNested.Nested ne = new WithNested.Nested(5);
        ne.f();
    }
}