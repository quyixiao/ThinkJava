package polymorphism;


/*****
 *
 *
 * 155页
 * 创建一个包含两个方法的基类，在第一个方法中可以调用第二个
 * 方法，然后产生一个继承自该基类的导出类，且覆盖基类这些方法，以便它们执行
 * 不同的行为，创建一个Robent数组，填充
 * 不同的Rodent类型，然后调用基类方法，观察
 * 发生什么情况
 *
 * 创建一个包含两个方法的基类，在第一个方法可以调用第
 * 二个方法，为该导出类创建一个对象，将它
 * 向上转型基类型并调用第一个方法，解释发生的
 * 情况
 *
 *
 *
 *
 *
 * 1
 *
 */
class TwoMethods {
    public void m1() {
        System.out.println("Inside m1, calling m2");
        m2();
    }

    public void m2() {
        System.out.println("Inside m2");
    }
}

class Inherited extends TwoMethods {
    public void m2() {
        System.out.println("Inside Inherited.m2");
    }
}

public class E10_MethodCalls {
    public static void main(String args[]) {
        TwoMethods x = new Inherited();
        x.m1();
    }
}