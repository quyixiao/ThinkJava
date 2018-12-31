package strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


//: strings/E10_CheckForMatch.java
/********************** Exercise 10 **********************
 * For the phrase "Java now has regular expressions"
 * evaluate whether the following expressions will find a
 * match:
 *
 *   ^Java
 *   \Breg.*
 *   n.w\s+h(a|i)s
 *   s?
 *   s*
 *   s+
 *   s{4}
 *   s{1}.
 *   s{0,3}
 ********************************************************/


/******
 * 301页
 * 对字符串java now has regular expressions 验证下列正则表达式是不能够发现一个匹配
 *
 * 1
 */
public class E10_CheckForMatch {
    public static void main(String[] args) {
        String source = "Java now has regular expressions";


        String[] regEx = {"^Java", "\\Breg.*",
                "n.w\\s+h(a|i)s", "s?", "s*", "s+", "s{4}", "s{1}.",
                "s{0,3}"};
        System.out.println("Source string: " + source);
        for (String pattern : regEx) {
            System.out.println("Regular expression: \"" + pattern + "\"");
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(source);
            while (m.find()) {
                System.out.println("Match \"" + m.group() +
                        "\" at positions " + m.start() + "-" +
                        (m.end() - 1));
            }
        }
    }
}