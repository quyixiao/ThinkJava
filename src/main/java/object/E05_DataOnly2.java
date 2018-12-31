package object;


/****
 *
 *
 * 37页
 *  修改前一个练习，将DataOnly中的数据在main()方法中赋值并打印出来
 *
 */
public class E05_DataOnly2 {
    public static void main(String[] args) {
        E04_DataOnly d = new E04_DataOnly();
        d.i = 47;
        System.out.println("d.i = " + d.i);
        d.d = 1.1;
        System.out.println("d.d = " + d.d);
        d.b = false;
        System.out.println("d.b = " + d.b);
    }
}