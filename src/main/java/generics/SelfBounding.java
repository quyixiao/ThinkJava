package generics;//: generics/SelfBounding.java


/****
 *
 *
 * 自限定所做的，就是要求在继承关系中，像下面这样的使用这个类
 *
 *
 *
 * 1
 * @param <T>
 */
class SelfBounded<T extends SelfBounded<T>> {
    T element;

    SelfBounded<T> set(T arg) {
        element = arg;
        return this;
    }

    T get() {
        return element;
    }
}

class A extends SelfBounded<A> {
}

class B extends SelfBounded<A> {
} // Also OK

class C extends SelfBounded<C> {
    C setAndGet(C arg) {
        set(arg);
        return get();
    }
}

class D {
}
// Can't do this:
// class E extends SelfBounded<D> {}
// Compile error: Type parameter D is not within its bound

// Alas, you can do this, so you can't force the idiom:
class F extends SelfBounded {
}

public class SelfBounding {
    public static void main(String[] args) {
        A a = new A();
        a.set(new A());
        a = a.set(new A()).get();
        a = a.get();
        C c = new C();
        c = c.setAndGet(new C());
    }
} ///:~
