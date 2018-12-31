//: generics/E23_FactoryConstraint2.java
/****************** Exercise 23 *****************
 * Modify FactoryConstraint.java so that create()
 * takes an argument.
 ************************************************/
package generics;

interface FactoryI1<T> {
    T create(int arg);
}

class Foo<T> {
    private T x;

    public Foo(FactoryI1<T> factory) {
        x = factory.create(1);
    }

    // ... }




}


class IntegerFactory1 implements FactoryI1<Integer> {
    public Integer create(int arg) {
        return new Integer(arg);
    }
}



class Widget1 {
    private final int id;

    Widget1(int ident) {
        id = ident;
    }

    public String toString() {
        return "Widget " + id;
    }

    public static class Factory implements FactoryI1<Widget1> {
        public Widget1 create(int arg) {
            return new Widget1(arg);
        }
    }
}