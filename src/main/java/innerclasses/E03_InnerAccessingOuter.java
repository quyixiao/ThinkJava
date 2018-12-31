//: innerclasses/E03_InnerAccessingOuter.java
/****************** Exercise 3 *****************
 * Modify Exercise 1 so Outer has a private
 * String field (initialized by the constructor),
 * and Inner has a toString() that displays this
 * field. Create an object of type Inner and
 * display it.
 ***********************************************/
package innerclasses;

/****
 * 194页
    修改练习1，使得Outer类包含一个Private String 域由构造器初始化经，而
 Inner包含一个显示这个域的toString()方法，创建一个Inner类型的对象并显示它

 1
 */
class Outer2 {
    private final String data;

    class Inner {
        public String toString() {
            return data;
        }
    }

    Outer2(String data) {
        this.data = data;
    }

    Inner getInner() {
        return new Inner();
    }
}

public class E03_InnerAccessingOuter {
    public static void main(String[] args) {
        Outer2 o = new Outer2("Inner accessing outer!");
        Outer2.Inner i = o.getInner();
        System.out.println(i.toString());
    }
}