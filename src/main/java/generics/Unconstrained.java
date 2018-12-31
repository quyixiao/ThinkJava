package generics;//: generics/Unconstrained.java


/***
 * 405页
 *
 *
 * 1
 */
class Other {
}

class BasicOther extends BasicHolder<Other> {


}

public class Unconstrained {
    public static void main(String[] args) {
        BasicOther b = new BasicOther(), b2 = new BasicOther();
        b.set(new Other());
        Other other = b.get();
        b.f();
    }
} /* Output:
Other
*///:~
