package concurrency.jvm;




public class MyTest2 {

    public static void main(String[] args) {
        System.out.println(MyParent2.str);
    }


}


class MyParent2{
    //public static  String str = "hello world";
    /***
     * 常量在编译阶段会存入到调用这个常量的方法所在的类的常量池中，
     * 本质上，调用类并没有直接引用到定义常量的类，因此 并不会触发
     * 定义常量的类的初始化
     * 注意：这里指的是将常量存放到了 MYTest2的常量池中，之后 Mytest2与 MyParent2就没有任何关系了
     * 甚至，我们可以将 MYParent2的 Class 文件删除
     *
     *
     * 助记符
     *      ldc:表示将 int,float 或是 String 类型的常量从常量池中推送至栈顶
     *      bipush表示将单字节 （-128 ~ 127 ）常量值推送到栈顶
     *      sipush 表示将一个短整形常量值（-32768~32767） 推送至栈顶
     *      iconst_1表示将 int 类型的1推送至栈顶(iconst_1 ~ iconst_5)
     *
     */
    public static final   String str = "hello world";


    public static final short a = 127;

    public static final int i = 128;


    public static final int m = 6;


    static {
        System.out.println("MyParent2 static block");
    }
}
