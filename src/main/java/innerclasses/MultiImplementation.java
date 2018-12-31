//: innerclasses/MultiImplementation.java
// With concrete or abstract classes, inner
// classes are the only way to produce the effect
// of "multiple implementation inheritance."
package innerclasses;


/*****
 *
 * 205页
 *  如果拥有的是抽象的类或具体的类，而不是接口，那就只能使用内部类才能实现多重继承
 *
 *
 *
 *      如果不需要解决，多重继承的问题，那么自然可以用别的方式编码，而不需要使用内部类
 *  但是如果使用内部类，还可以获得其他的一些特性
 *
 *      1.内部类可以有多个实例，每个实例都有自己状态信息，并且与外围类对象的信息相互独立
 *      2.在单个外围类中，可以让多个内部类以不同的方式实现同一个接口，或继承同一个类，
 *      稍后就会展示一个这样的例子
 *      3.创建内部类对象的时刻并不依赖于外围类对象的创建
 *      4.内部类没有令人类迷惑的"is-a"的关系，它就是一个独立的实体
 *
 *
 *  1
 *
 *
 */
class D {
}

abstract class E {
}

class Z extends D {
    E makeE() {
        return new E() {
        };
    }
}

public class MultiImplementation {
    static void takesD(D d) {
    }

    static void takesE(E e) {
    }

    public static void main(String[] args) {
        Z z = new Z();
        takesD(z);
        takesE(z.makeE());
    }
} ///:~
