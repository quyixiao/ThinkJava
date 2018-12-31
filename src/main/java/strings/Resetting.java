package strings;//: strings/Resetting.java

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/****
 *
 * 307页
 *
 * 骑过reset()方法，可以将现有的Matcher对象应用于一个新的字符序列
 *
 *
 * 1
 */
public class Resetting {
    public static void main(String[] args) throws Exception {
        Matcher m = Pattern.compile("[frb][aiu][gx]")
                .matcher("fix the rug rig with bags rags");
        while (m.find()) {
            System.out.print(m.group() + " ");
        }
        System.out.println();
        m.reset("fix the rig with rags");
        while (m.find()) {
            System.out.print(m.group() + " ");
        }
    }
} /* Output:
fix rug bag
fix rig rag
*///:~
