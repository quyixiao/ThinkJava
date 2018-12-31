//: reusing/E09_ConstructorOrder2.java
/****************** Exercise 9 *****************
 * Create a class called Root and an instance of
 * each of three classes, Component1, Component2, and
 * Component3. Derive a class Stem from Root that
 * also contains an instance of each "component."
 * Default constructors for each class should
 * print a message about that class.
 ***********************************************/
package reusing;


/***
 *
 * 130页
 * 创建一个Root类，令其含有名为Component1,Component2,Component3的类
 * 各一个实例，这些也由你写，从Root中派生一个类Stem，也含有上述各组成部分，所有的类
 * 都应带有可打印出类的相关信息的默认构造器。
 *
 */
class Component1 {
    public Component1() {
        System.out.println("Component1");
    }
}

class Component2 {
    public Component2() {
        System.out.println("Component2");
    }
}

class Component3 {
    public Component3() {
        System.out.println("Component3");
    }
}

class Root {
    Component1 c1 = new Component1();
    Component2 c2 = new Component2();
    Component3 c3 = new Component3();

    public Root() {
        System.out.println("Root");
    }
}

class Stem extends Root {
    Component1 c1 = new Component1();
    Component2 c2 = new Component2();
    Component3 c3 = new Component3();

    public Stem() {
        System.out.println("Stem");
    }
}

public class E09_ConstructorOrder2 {
    public static void main(String args[]) {
        new Stem();
    }
}