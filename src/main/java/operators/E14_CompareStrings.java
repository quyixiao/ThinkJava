package operators;


/*******
 * 63页
 * 编写一个接收两个字符串，然后把结果打印出来，做==和！=比较的同时，用equals()作测试，在main()里面用几个不同的字符串对象调用这个方法
 *
 */
public class E14_CompareStrings {
    public static void p(String s, boolean b) {
        System.out.println(s + ": " + b);
    }

    public static void compare(String lval, String rval) {
        System.out.println("lval: " + lval + " rval: " + rval);
        //! p("lval < rval: " + lval < rval);
        //! p("lval > rval: " + lval > rval);
        //! p("lval <= rval: " + lval <= rval);
        //! p("lval >= rval: " + lval >= rval);
        p("lval == rval", lval == rval);
        p("lval != rval", lval != rval);
        p("lval.equals(rval)", lval.equals(rval));
    }

    public static void main(String[] args) {
        compare("Hello", "Hello");
        // Force creation of separate object:
        String s = new String("Hello");
        compare("Hello", s);
        compare("Hello", "Goodbye");
    }
}