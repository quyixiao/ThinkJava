//: typeinfo/E22_SimpleDynamicProxyDemo.java
/****************** Exercise 22 *****************
 * Modify SimpleDynamicProxy.java so that it
 318 Thinking in Java, 4th Edition Annotated Solution Guide
 * measures method-call times.
 ***********************************************/
package typeinfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/****
 * 340
 *
 *
 * 1
 */
class DynamicProxyHandler1 implements InvocationHandler {

    private Object proxied;

    public DynamicProxyHandler1(Object proxied) {
        this.proxied = proxied;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("**1111111111** proxy: " + proxy.getClass() +
                ", method: " + method + ", args: " + args);
        if (args != null)
            for (Object arg : args)
                System.out.println("  " + arg);
        long start = System.nanoTime();
        Object ret = method.invoke(proxied, args);
        long duration = System.nanoTime() - start;
        System.out.println("METHOD-CALL TIME: " + duration);
        return ret;
    }
}

public class E22_SimpleDynamicProxyDemo {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }

    public static void main(String[] args) {
        RealObject real = new RealObject();
        consumer(real);
        System.out.println("==========================");
        Interface proxy = (Interface) Proxy.newProxyInstance(
                Interface.class.getClassLoader(),
                new Class<?>[]{Interface.class},
                new DynamicProxyHandler1(real));
        consumer(proxy);
    }
} 