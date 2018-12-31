//: strings/E12_Groups2.java
/************************** Exercise 12 ******************
 * Modify Groups.java to count all unique words
 * with no initial capital letter.
 *********************************************************/
package strings;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.mindview.util.Print.print;


/*****
 *
 * 303
 * 修改Goups.java类，推荐理由所有不以大写字母开头的词，不重复的计算其个数
 *
 * 1
 */

public class E12_Groups2 {
    public static void main(String[] args) {
        Set<String> words = new HashSet<String>();
        Matcher m = Pattern.compile("\\b((?![A-Z])\\w+)\\b").matcher(Groups.POEM);
        while (m.find()) {
            words.add(m.group(1));
        }
        print("Number of unique words = " + words.size());
        print(words.toString());
    }
}