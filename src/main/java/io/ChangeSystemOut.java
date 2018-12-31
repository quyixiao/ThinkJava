package io;//: io/ChangeSystemOut.java
// Turn System.out into a PrintWriter.

import java.io.PrintWriter;


/**
 *
 *
 * 549 页
 * System.out 是一个PrintStream ，而PrintStream 是一个OutputStream ,PrintWriter 有一个
 * 可以接受OutputStream 作为参数的构造器，因此，只要需要，就可以使用那个构造器把System.out 转换成
 * PrintWriter
 *
 * 重要的是要使用有两个的PrintWriter的构造器，并将第二个参数设置为treu,以便开启自动清空功能，否则，你可能
 * 看不到输出
 *
 *
 * java 的system 类提供了一些简单的静态方法调用，以允许我们对标准输入，输出和错误I/O 流进行重定向
 * setIn(intputStream )
 * setOut(PrintStream)
 * setErr(PrintStream)
 *
 *
 *
 * 如果我们突然开始在显示器上创建大量的输出，而这些输出滚动得至于无法阅读的时候，重定向输出就显得极为重要有用了，对
 * 于我们想测试某个特定的用户输入序列的命令行程序来说重定向输入就很有价值，下例简单的演示了这些方法的使用；
 *
 *
 */
public class ChangeSystemOut {
    public static void main(String[] args) {
        //PrintWriter out = new PrintWriter(System.out);
        PrintWriter out = new PrintWriter(System.out,true);
        out.println("Hello, world");
    }
} /* Output:
Hello, world
*///:~
