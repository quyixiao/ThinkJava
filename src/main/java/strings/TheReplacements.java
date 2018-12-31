package strings;//: strings/TheReplacements.java

import net.mindview.util.TextFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.mindview.util.Print.print;

/*! Here's a block of text to use as input to
    the regular expression matcher. Note that we'll
    first extract the block of text by looking for
    the special delimiters, then process the
    extracted block. !*/

/***
 *
 * 306
 *
 * 下面的程序演示了如何使用这引进替换的方法，开头
 *
 *
 *
 * 1
 *
 */
public class TheReplacements {
    public static void main(String[] args) throws Exception {
        String s = TextFile.read("/Users/quyixiao/Desktop/source/ThinkJava/src/main/java/strings/TheReplacements.java");
        // Match the specially commented block of text above:
        //匹配/*!和!*/之间的所有文字，注意分组的括号
        Matcher mInput = Pattern.compile("/\\*!(.*)!\\*/", Pattern.DOTALL).matcher(s);
        if (mInput.find()) {
            s = mInput.group(1); // Captured by parentheses
        }
        // Replace two or more spaces with a single space:
        //将两个或两个以上的空格的地方，缩减为一个空格，并且删除每个开头部分的所有的空格
        //为了使每一行都达到这相效果，而不仅仅只是删除文本开头部分的空格，这里特意的打开了多选状态，

        s = s.replaceAll(" {2,}", " ");
        // Replace one or more spaces at the beginning of each
        // line with no spaces. Must enable MULTILINE mode:
        s = s.replaceAll("(?m)^ +", "");
        print(s);
        s = s.replaceFirst("[aeiou]", "(VOWEL1)");
        StringBuffer sbuf = new StringBuffer();
        Pattern p = Pattern.compile("[aeiou]");
        Matcher m = p.matcher(s);
        // Process the find information as you
        // perform the replacements:
        // 你可以遍历执行所有的替换操作，然后再调用appendTail()方法，但是，如果你想模拟
        // replaceFirst(或替换n次)的行为，那就只需要执行一次替换，然后调用appendTail()方法，
        // 将剩余的处理的部分存入sbub即可，同时appendReplacement()方法还允许你通过$g直接找到匹配的某个组
        // 这里的g就是组号，铭感五内，这只能就会一些简单的处理，无法实现类似前面的这个例子中的功能

        while (m.find())
            m.appendReplacement(sbuf, m.group().toUpperCase());
        // Put in the remainder of the text:
        m.appendTail(sbuf);
        print(sbuf);
    }
}






/* Output:
Here's a block of text to use as input to
the regular expression matcher. Note that we'll
first extract the block of text by looking for
the special delimiters, then process the
extracted block.
H(VOWEL1)rE's A blOck Of tExt tO UsE As InpUt tO
thE rEgUlAr ExprEssIOn mAtchEr. NOtE thAt wE'll
fIrst ExtrAct thE blOck Of tExt by lOOkIng fOr
thE spEcIAl dElImItErs, thEn prOcEss thE
ExtrActEd blOck.
*///:~
