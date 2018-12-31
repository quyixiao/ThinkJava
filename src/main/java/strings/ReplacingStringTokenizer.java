package strings;//: strings/ReplacingStringTokenizer.java

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;


/****
 *
 *
 * 312 页
 *
 *
 * 使用正则表达式或Scanner对象，我们能够以更加复杂的模式来分割一个字符串，而这对于
 * StringTokenizer来说，就很困难了，基本上，我们可以放心的说，StringTokenizer己经
 * 可以废弃不用了
 *
 *
 *
 * 1
 *
 *
 *      过去，java对字符串的操作的支持相当的不完善，不过随着近几个版本的升级，我们可以看到
 * Java己经从其他语言中吸取了许多的成熟的经验，到目前为止，它对字符串的操作的支持己经很完善了
 * ，不过，有时你还要在细节上注意效率问题，例如恰当的使用StringBuilder等
 *
 *
 *
 *
 *
 *
 */
public class ReplacingStringTokenizer {
    public static void main(String[] args) {
        String input = "But I'm not dead yet! I feel happy!";
        StringTokenizer stoke = new StringTokenizer(input);
        while (stoke.hasMoreElements())
            System.out.print(stoke.nextToken() + " ");
        System.out.println();
        System.out.println(Arrays.toString(input.split(" ")));
        Scanner scanner = new Scanner(input);
        while (scanner.hasNext())
            System.out.print(scanner.next() + " ");
    }
} /* Output:
But I'm not dead yet! I feel happy!
[But, I'm, not, dead, yet!, I, feel, happy!]
But I'm not dead yet! I feel happy!
*///:~
