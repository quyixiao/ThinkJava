//: strings/E20_Scanner.java
/********************** Exercise 20 *********************
 * Create a class that contains int, long, float and double
 * and String fields. Create a constructor for this class
 * that takes a single String argument, and scans that
 * string into the various fields. Add a toString() method
 * and demonstrate that your class works correctly.
 ********************************************************/
package strings;

import java.util.Locale;
import java.util.Scanner;


/******
 *
 *
 *
 * 310页
 *      编写一个包含int,long,float,double和String属性的类，为它编写一个构造器
 *  接收一个String参数，然后扫描该字符串，为和个属性赋值，再添加一个toString()方法
 *  用来演示你的类是否工作正确
 *
 *
 *  1
 *  
 */
class DataHolder2 {
    private int i;
    private long l;
    private float f;
    private double d;
    private String s;

    DataHolder2(String data) {
        Scanner stdin = new Scanner(data);
        stdin.useLocale(Locale.US);
        i = stdin.nextInt();
        l = stdin.nextLong();
        f = stdin.nextFloat();
        d = stdin.nextDouble();
        s = stdin.next("\\w+");
    }

    public String toString() {
        return i + " " + l + " " + f + " " + d + " " + s;
    }
}

public class E20_Scanner {
    public static void main(String[] args) {
        DataHolder2 dh =
                new DataHolder2("1 10000000000000 1.1 1e55 Howdy Hi");
        System.out.println(dh.toString());
    }
} 