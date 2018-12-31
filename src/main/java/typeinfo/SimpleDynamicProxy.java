package typeinfo;//: typeinfo/SimpleDynamicProxy.java

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/****
 *
 *
 *
 * 338页
 *
 *      通过调用静态方法，Proxy.newProxyInstance()可以创建动态的代理，这个方法需要得到一个类
 * 加载器，（你通常可能从被加载的对象中获取其类加载器，然后传递给它），一个你希望该代理实现的接口列表
 * 不是类或抽象类，以及InvocationHandler接口的一个实现，动态代理可以将所有的调用重定向到调用处理器，
 * 在执行其中介任务时，可以将请求转发
 *
 *
 *
 * 因为接口的调用将被重定向为代理的调用
 *
 * 1
 */
class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("**** proxy: " + proxy.getClass() + ", method: " + method + ", args: " + args);
        if (args != null)
            for (Object arg : args)
                System.out.println("  " + arg);
        return method.invoke(proxied, args);
    }
}

class SimpleDynamicProxy {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }

    public static void main(String[] args) {
        RealObject real = new RealObject();
        consumer(real);
        // Insert a proxy and call again:
        Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(),
                new Class[]{Interface.class},
                new DynamicProxyHandler(real));
        consumer(proxy);
    }
}







/* Output: (95% match)
doSomething
somethingElse bonobo
**** proxy: class $Proxy0, method: public abstract void Interface.doSomething(), args: null
doSomething
**** proxy: class $Proxy0, method: public abstract void Interface.somethingElse(java.lang.String), args: [Ljava.lang.Object;@42e816
  bonobo
somethingElse bonobo
*///:~
