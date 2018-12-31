package object;

/****
 * 37 页
 * 练习1
 *  创建一个对象，它包含一个int 域和一个char域，它们都没有被初始化，将它们的值打印出来，以验证Java执行了默认的初始化
 *
 */
public class E01_DefaultInitialization {
    int i;
    char c;

    public E01_DefaultInitialization() {
        System.out.println("i = " + i);
        System.out.println("c = [" + c + ']');
    }

    public static void main(String[] args) {
        new E01_DefaultInitialization();
    }
}

