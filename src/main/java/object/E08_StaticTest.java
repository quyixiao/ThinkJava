package object;


/*****
 * 37 页
 *  编写一个程序，无论你创建了某个特定类的多少个对象，这个类中的某个特定的static域只有一个实例
 *
 *
 */
public class E08_StaticTest {
    static int i = 47;

    public static void main(String[] args) {
        E08_StaticTest st1 = new E08_StaticTest();
        E08_StaticTest st2 = new E08_StaticTest();
        System.out.println(st1.i + " == " + st2.i);
        st1.i++;
        System.out.println(st1.i + " == " + st2.i);
    }
}