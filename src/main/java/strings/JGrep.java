package strings;//: strings/JGrep.java
// A very simple version of the "grep" program.
// {Args: JGrep.java "\\b[Ssct]\\w+"}

import net.mindview.util.TextFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JGrep {
    public static void main(String[] args) throws Exception {
       String a= "main";

        Pattern p = Pattern.compile(a);
        // Iterate through the lines of the input file:
        int index = 0;
        Matcher m = p.matcher("");
        for (String line : new TextFile("/Users/quyixiao/Desktop/source/ThinkJava/src/main/java/strings/JGrep.java")) {
            System.out.println(line);
            m.reset(line);
            while (m.find())
                System.out.println(index++ + ": " + m.group() + ": " + m.start());
        }
    }
} /* Output: (Sample)
0: strings: 4
1: simple: 10
2: the: 28
3: Ssct: 26
4: class: 7
5: static: 9
6: String: 26
7: throws: 41
8: System: 6
9: System: 6
10: compile: 24
11: through: 15
12: the: 23
13: the: 36
14: String: 8
15: System: 8
16: start: 31
*///:~
