package exceptions;//: exceptions/NeverCaught.java
// Ignoring RuntimeExceptions.
// {ThrowsException}


/*****
 *      请务必记住，只能在代码中忽略runTimeException 及其子类，类型的异常，其他类型的异常
 *  处理都是由编译器强制实施的，究其原因，RuntimeException代表的是编程错误
 *
 *      无论try块里发生了什么，内存总能得到释放，但java有垃圾回收机制，所以内存释放不再
 *  是问题，而且，Java也没有析构函数可供调用，那么，java在什么情况下才能用到finally呢？
 *
 *  己经打开的文件或网络连接，在屏幕上画的图形，甚至可以是外部世界是某个开关
 *
 *
 *
 *
 *
 */
public class NeverCaught {
    static void f() {
        throw new RuntimeException("From f()");
    }

    static void g() {
        f();
    }

    public static void main(String[] args) {
        g();
    }
} ///:~
