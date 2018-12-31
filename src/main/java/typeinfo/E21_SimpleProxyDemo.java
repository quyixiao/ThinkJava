//: typeinfo/E21_SimpleProxyDemo.java
/****************** Exercise 21 *****************
 * Modify SimpleProxyDemo.java so it measures
 * method-call times.
 ***********************************************/
package typeinfo;

import static net.mindview.util.Print.print;


/****
 *
 *
 *
 *
 * 340页
 *
 *  修改SimpleProxyDemo.java ，使其可以度量方法调用的次数
 *
 *
 * 1
 *
 */
public class E21_SimpleProxyDemo {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }

    public static void main(String[] args) {
        consumer(new RealObject());
        consumer(new SimpleProxy(new RealObject()));
    }
}