
//: strings/E15_JGrep2.java
// {Args: E15_JGrep2.java "\b[Ssct]\w+" CASE_INSENSITIVE}
/*********************** Exercise 15 *********************
 * Modify JGrep.java to accept flags as arguments (e.g.,
 * Pattern.CASE_INSENSITIVE, Pattern.MULTILINE).
 *********************************************************/
package strings;

import net.mindview.util.TextFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.mindview.util.Print.print;

/******
 *
 *
 * 308页
 *  修改JGrep.java类，令其能够的接受模式标志参数，例如 （PAttern.CASE_INSENSITIVE,Pattern.MULTILINE）
 */
public class E15_JGrep2 {
    public static void main(String[] args) throws Exception {


        int flag = 0;

        String args2 = "CANON_EQ1";
        if (args2.equalsIgnoreCase("CANON_EQ")) {
            flag = Pattern.CANON_EQ;
        } else if (args2.equalsIgnoreCase("CASE_INSENSITIVE")) {
            flag = Pattern.CASE_INSENSITIVE;
        } else if (args2.equalsIgnoreCase("COMMENTS")) {
            flag = Pattern.COMMENTS;
        } else if (args2.equalsIgnoreCase("DOTALL")) {
            flag = Pattern.DOTALL;
        } else if (args2.equalsIgnoreCase("MULTILINE")) {
            flag = Pattern.MULTILINE;
        } else if (args2.equalsIgnoreCase("UNICODE_CASE")) {
            flag = Pattern.UNICODE_CASE;
        } else if (args2.equalsIgnoreCase("UNIX_LINES")) {
            flag = Pattern.UNIX_LINES;
        }

        Pattern p = Pattern.compile("main", flag);
        // Iterate through the lines of the input file:
        int index = 0;
        Matcher m = p.matcher("");
        for (String line : new TextFile("/Users/quyixiao/Desktop/source/ThinkJava/src/main/java/strings/JGrep.java")) {
            //System.out.println(line);
            m.reset(line);
            while (m.find())
                print(index++ + ": " + m.group() + ": " +
                        m.start());
        }
    }
} 