package innerclasses;//: innerclasses/InheritInner.java
// Inheriting an inner class.


/****
 *
 * 212页
 *
 *      可以看到，InheritInner只继承自己内部类，而不是外围类，但是当要生成一个构造器时，默认的
 * 构造器并不算好，而且不能只是传递一个指向外围类的对象的引用，此外，必须在构造器内使用如下语法
 *
 */
class WithInner {
    class Inner {
    }
}

public class InheritInner extends WithInner.Inner {
    //! InheritInner() {} // Won't compile
    InheritInner(WithInner wi) {
        wi.super();
    }

    public static void main(String[] args) {
        WithInner wi = new WithInner();
        InheritInner ii = new InheritInner(wi);
    }
} ///:~
