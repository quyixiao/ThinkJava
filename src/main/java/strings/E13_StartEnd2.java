//: strings/E13_StartEnd2.java
/************************** Exercise 13 ******************
 * Modify StartEnd.java so that it uses Groups.POEM as
 * input, but still produces positive outputs for find(),
 * lookingAt() and matches().
 *********************************************************/
package strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.mindview.util.Print.print;


/******
 *
 *  304页
 *      修改startEnd.java，让它使用Groups.POEM为输入，必要时修改正则表达式
 * 使find()，lokingAt()和Matches()都有机会匹配成功
 */
public class E13_StartEnd2 {
    private static class Display {
        private boolean regexPrinted = false;
        private String regex;

        Display(String regex) {
            this.regex = regex;
        }

        void display(String message) {
            if (!regexPrinted) {
                print(regex);
                regexPrinted = true;
            }
            print(message);
        }
    }

    static void examine(String s, String regex) {
        Display d = new Display(regex);
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);
        while (m.find())
            d.display("find() '" + m.group() +
                    "' start = " + m.start() + " end = " + m.end());
        if (m.lookingAt()) // No reset() necessary
            d.display("lookingAt() start = "
                    + m.start() + " end = " + m.end());
        if (m.matches()) // No reset() necessary
            d.display("matches() start = "
                    + m.start() + " end = " + m.end());
    }

    public static void main(String[] args) {
        for (String in : Groups.POEM.split("\n")) {
            print("input : " + in);
            for (String regex : new String[]{"\\w*ere\\w*",
                    "\\w*at", "t\\w+", "T.*?."})
                examine(in, regex);
        }
    }
} 