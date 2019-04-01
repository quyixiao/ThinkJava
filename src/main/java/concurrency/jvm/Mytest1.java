package concurrency.jvm;


/***
 * 对于静态字段来说，只有直接定义了该字段的类才能会被初始化；
 * 当一个类在初始化时，要求其父类全部都已经初始化完毕了
 *
 * -XX:+TraceClassLoading
 *
 * -XX:+<option> ,表示开户option 选项
 * -XX:-<option>，表示关闭 option 选项
 * -XX:<option>,表示将 option 选项的值设置为 value
 *
 *
 *
 *
 */
public class Mytest1 {


    public static void main(String[] args) {
       // System.out.println(MyChild1.str);
        System.out.println("===================================");
        System.out.println(MyChild1.str2);

    }
}


class MyParent1{
    public static String str = "hello world";

    static {
        System.out.println("MyParent1 static block");
    }
}


class MyChild1 extends MyParent1{
    public static String str2 = "iodsosd";
    static {
        System.out.println("MyChild1 static block ");
    }
}
