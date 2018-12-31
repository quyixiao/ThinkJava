package generics;//: generics/FactoryConstraint.java


/***
 *
 *
 *
 * 382 页
 *
 *
 * 1
 * @param <T>
 */
interface FactoryI<T> {
    T create();
}

class Foo2<T> {
    private T x;

    public <F extends FactoryI<T>> Foo2(F factory) {
        x = factory.create();
    }
    // ...


    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }
}

class IntegerFactory implements FactoryI<Integer> {
    public Integer create() {
        return new Integer(0);
    }
}

class Widget {
    public static class Factory implements FactoryI<Widget> {
        public Widget create() {
            return new Widget();
        }
    }
}

public class FactoryConstraint {
    public static void main(String[] args) {
        Foo2<Integer> f1 = new Foo2<Integer>(new IntegerFactory());
        System.out.println(f1.getX());

        Foo2<Widget> f2 = new Foo2<Widget>(new Widget.Factory());
        System.out.println(f2.getX());


    }
}


///:~
