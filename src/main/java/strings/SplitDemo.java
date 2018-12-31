package strings;//: strings/SplitDemo.java

import java.util.Arrays;
import java.util.regex.Pattern;

import static net.mindview.util.Print.print;


/*****
 *
 *
 * 第二种形式的split()方法可以限制将输入分割成字符串的数量
 *
 *
 * */
public class SplitDemo {
    public static void main(String[] args) {
        String input =
                "This!!unusual use!!of exclamation!!points";
        print(Arrays.toString(
                Pattern.compile("!!").split(input)));
        // Only do the first three:
        print(Arrays.toString(
                Pattern.compile("!!").split(input, 3)));
    }
} /* Output:
[This, unusual use, of exclamation, points]
[This, unusual use, of exclamation!!points]
*///:~
