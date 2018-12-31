//: generics/E02_Holder4.java
/****************** Exercise 2 *****************
 * Create a holder class that holds three objects
 * of the same type, along with the methods to
 * store and fetch those objects and a constructor
 * to initialize all three.
 ************************************************/
package generics;

class Holder4<T> {
    private T a, b, c;

    public Holder4(T a, T b, T c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void setA(T a) {
        this.a = a;
    }

    public void setB(T b) {
        this.b = b;
    }

    public void setC(T c) {
        this.c = c;
    }

    public T getA() {
        return a;
    }

    public T getB() {
        return b;
    }

    public T getC() {
        return c;
    }
}


/******
 *
 *
 *
 * 354页
 *
 * 创建一个Holder类，今天工作能够持有具有相同类型的3个对象，并提供相应的读写方法访问这些
 * 对象，以及一个可以其持有的3个对象的构造器
 *
 *
 *
 * 1
 *
 */
public class E02_Holder4 {
    public static void main(String[] args) {
        Holder4<String> h4 = new Holder4<String>("A", "B", "C");
        System.out.println(h4.getA());
        System.out.println(h4.getB());
        System.out.println(h4.getC());

        h4.setC("D");
        System.out.println(h4.getC());
    }




}