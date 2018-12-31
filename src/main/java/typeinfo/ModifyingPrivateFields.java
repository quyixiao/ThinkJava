package typeinfo;//: typeinfo/ModifyingPrivateFields.java

import java.lang.reflect.Field;


/****
 *
 *
 * 349
 *
 *
 * 1
 *
 *
 *      看起来没有任何方式可以阻止反射到达并调用那些非公共访问权限的方法，对于域来说，的确如此
 * ，即便是Private域
 *
 *
 *
 *      但是，final域实际上在遭遇修改时是安全的，运行时系统会在不抛出异常的情况下的接受任何修尝试
 *  但是实际上不会发生任何修改
 *
 *
 *
 *      这些问题将难以或者不可能解决
 *      通常反射带来的好处是不可否认的
 *
 *
 *
 *
 *
 *
 */
class WithPrivateFinalField {
    private int i = 1;
    private int j = 2;
    private final String s = "I'm totally safe";
    private String s2 = "Am I safe?";
    public String toString() {
        return "i = " + i + ", " + s + ", " + s2;
    }
}

public class ModifyingPrivateFields {
    public static void main(String[] args) throws Exception {
        WithPrivateFinalField pf = new WithPrivateFinalField();
        System.out.println(pf);




        Field f = pf.getClass().getDeclaredField("j");

        f.setAccessible(true);

        System.out.println("f.getInt(pf): " + f.getInt(pf));


        f.setInt(pf, 47);
        System.out.println(pf);
        f = pf.getClass().getDeclaredField("s");

        f.setAccessible(true);

        System.out.println("f.get(pf): " + f.get(pf));
        f.set(pf, "No, you're not!");
        System.out.println(pf);

        f = pf.getClass().getDeclaredField("s2");
        f.setAccessible(true);

        System.out.println("f.get(pf): " + f.get(pf));
        f.set(pf, "No, you're not!");
        System.out.println(pf);
    }
} /* Output:
i = 1, I'm totally safe, Am I safe?
f.getInt(pf): 1
i = 47, I'm totally safe, Am I safe?
f.get(pf): I'm totally safe
i = 47, I'm totally safe, Am I safe?
f.get(pf): Am I safe?
i = 47, I'm totally safe, No, you're not!
*///:~
