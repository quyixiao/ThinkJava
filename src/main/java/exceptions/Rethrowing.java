package exceptions;//: exceptions/Rethrowing.java
// Demonstrating fillInStackTrace()


/*****
 *
 *
 *
 * 258页
 *
 *
 *
 *
 *      重抛异常会把异常抛给上一级环境中的异常处理程序，同一个try块后续catch子句将被忽略
 * ，此外，异常对象的所有的信息都得以保持，所以高一级的环境中捕获此异常处理程序可以从这个异常
 * 中得对象中得到所有的信息
 *      如果只是把当前异常对象重新抛出，那么printStackTrace()方法显示的将原来异常抛出点
 *  的调用栈信息，而并非重新抛出点的信息，要想更新这个信息，可以调用fillInStackTrace()方法
 *  这将返回一个Throwable对象，它是通过把当前调用栈信息填入原来那个异常对象而建立的，就像
 *  这样
 *
 *
 *
 *
 *  1
 *
 */
public class Rethrowing {
    public static void f() throws Exception {
        System.out.println("originating the exception in f()");
        throw new Exception("thrown from f()");
    }

    public static void g() throws Exception {
        try {
            f();
        } catch (Exception e) {
            System.out.println("Inside g(),e.printStackTrace()");
            e.printStackTrace(System.out);
            throw e;
        }
    }

    public static void h() throws Exception {
        try {
            f();
        } catch (Exception e) {
            System.out.println("Inside h(),e.printStackTrace()");
            e.printStackTrace(System.out);
            throw (Exception) e.fillInStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            g();
        } catch (Exception e) {
            System.out.println("main: printStackTrace()");
            e.printStackTrace(System.out);
        }
        try {
            h();
        } catch (Exception e) {
            System.out.println("main: printStackTrace()");
            e.printStackTrace(System.out);
        }
    }
}