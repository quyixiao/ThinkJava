package concurrency;

public class Test {

    // 存一个
    public static void main(String[] args) {


        String a = "abc";
        String b = "abc";

        String c = new String("abc");

        System.out.println(a == b );

        System.out.println(c == a);

        System.out.println(a == c.intern());

        System.out.println(a.intern() == c);

    }
}
