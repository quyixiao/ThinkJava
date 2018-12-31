//: reusing/E08_CallBaseConstructor.java
/****************** Exercise 8 *****************
 * Create a base class with only a non-default
 * constructor, and a derived class with both a
 * default (no-arg) and non-default constructor.
 * Call the base-class constructor in the
 * derived-class constructors.
 ***********************************************/
package reusing;

class BaseNonDefault {
    public BaseNonDefault(int i) {
    }
}

class DerivedTwoConstructors extends BaseNonDefault {
    public DerivedTwoConstructors() {
        super(47);
    }

    public DerivedTwoConstructors(int i) {
        super(i);
    }
}