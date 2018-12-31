//: initialization/E08_ThisMethodCall.java
/****************** Exercise 8 *****************
 * Create a class with two methods. Within the
 * first method call the second method twice to
 * see it work, the first time without using this,
 * and the second time using this.
 * (You should not use this form in practice.)
 ***********************************************/
package initialization;


/****
 * 85页
 * 编写具有两个方法的类，在第一个方法内调用第二个方法两次，第一次调用时不使用this关键字
 * ，第二次调用时使用this关键字，这里只是为了验证它是起作用的，你不应
 * 该在实践中使用这种方式
 *
 *
 * 1
 *
 *
 */

public class E08_ThisMethodCall {
    public void a() {
        b();
        this.b();
    }

    public void b() {
        System.out.println("b() called");
    }

    public static void main(String args[]) {
        new E08_ThisMethodCall().a();
    }
}