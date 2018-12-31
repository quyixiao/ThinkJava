package object;


/****
 * 37 页
 *  缩写一个程序，编写自动包装功能对所有的基本类型和包装器类型都起作用
 *
 */
public class E09_AutoboxingTest {
    public static void main(String[] args) {
        Byte by = 1;
        byte bt = by;
        System.out.println("byte = " + bt);
        Short sh = 1;
        short s = sh;
        System.out.println("short = " + s);
        Integer in = 1;
        int i = in;
        System.out.println("int = " + i);
        Long lo = 1L;
        long l = lo;
        System.out.println("long = " + l);
        Boolean bo = true;
        boolean b = bo;
        System.out.println("boolean = " + b);
        Character ch = 'x';
        char c = ch;
        System.out.println("char = " + c);
        Float fl = 1.0f;
        float f = fl;
        System.out.println("float = " + f);
        Double db = 1.0d;
        double d = db;
        System.out.println("double = " + d);
    }
}