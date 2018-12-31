//: typeinfo/E23_SimpleDynamicProxyDemo2.java
// {ThrowsException}
/****************** Exercise 23 *****************
 * Inside invoke() in SimpleDynamicProxy.java,
 * try to print the proxy argument and explain
 * what happens.
 ************************************************/
package typeinfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/*****
 *
 *
 *
 * 340页
 *
 *
 *
 * 在simpleDynamicProxy.java中的inmoke()内部，尝试打印Proxy参数并解释所产生的
 * 结果
 *
 *
 *
 * 1
 *
 */
class DynamicProxyHandler2 implements InvocationHandler {
    private Object proxied;

    public DynamicProxyHandler2(Object proxied) {
        this.proxied = proxied;
    }

    public Object
    invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("proxy: " + proxy );
        return method.invoke(proxied, args);
    }
}

public class E23_SimpleDynamicProxyDemo2 {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }

    public static void main(String[] args) {
        RealObject real = new RealObject();
        consumer(real);
        System.out.println("============================");
        Interface proxy = (Interface) Proxy.newProxyInstance(
                Interface.class.getClassLoader(),
                new Class<?>[]{Interface.class},
                new DynamicProxyHandler2(real));
        consumer(proxy);
    }
} 