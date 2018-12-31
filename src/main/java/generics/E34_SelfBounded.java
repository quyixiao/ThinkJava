//: generics/E34_SelfBounded.java
/****************** Exercise 34 *****************
 * Create a self-bounded generic type that contains
 * an abstract method that takes an argument of the
 * generic type parameter and produces a return value
 * of the generic type parameter. In a non-abstract
 * method of the class, call the abstract method and
 * return its result. Inherit from the self-bounded
 * type and test the resulting class.
 ************************************************/
package generics;

abstract class
GenericProcessor<T extends GenericProcessor<T>> {
    abstract T process(T arg);

    T test() {
        return process(null);
    }
}

class ConcreteProcessor
        extends GenericProcessor<ConcreteProcessor> {
    ConcreteProcessor process(ConcreteProcessor arg) {
        if (arg == null)
            return this;
        return arg;
    }
}


/*****
 *
 *
 *
 * 409
 *  创建一个自限定的泛型类型，它包含一个abstract方法，这个方法将的将接受一个
 *  泛型类型的参数，并产生具有这个泛型参数的返回值，在这个类的非abstract方法中，调用这个
 *  abstract方法，并返回其结果，继承这个自限定类型，并测试所产生的类
 *
 */
public class E34_SelfBounded {
    public static void main(String[] args) {
        ConcreteProcessor cp = new ConcreteProcessor();
        System.out.println(cp == cp.test());
    }
}