package strings;//: strings/ThreatAnalyzer.java

import java.util.Scanner;
import java.util.regex.MatchResult;


/****
 * 311页
 *
 *
 *
 *
 * 有一点是需要注意的，它仅仅针对下一个输入分词进行匹配，如果你的正则表达式中
 * 含有定界符，那永远都不可能匹配成功
 *
 *
 *
 *
 *
 * 1
 */
public class ThreatAnalyzer {
    static String threatData =
            "58.27.82.161@02/10/2005\n" +
                    "204.45.234.40@02/11/2005\n" +
                    "58.27.82.161@02/11/2005\n" +
                    "58.27.82.161@02/12/2005\n" +
                    "58.27.82.161@02/12/2005\n" +
                    "[Next log section with different data format]";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(threatData);

        String pattern = "(\\d+[.]\\d+[.]\\d+[.]\\d+)@" +
                "(\\d{2}/\\d{2}/\\d{4})";

        while (scanner.hasNext(pattern)) {

            scanner.next(pattern);

            MatchResult match = scanner.match();
            String ip = match.group(1);
            String date = match.group(2);
            System.out.format("Threat on %s from %s\n", date, ip);
        }
    }
} /* Output:
Threat on 02/10/2005 from 58.27.82.161
Threat on 02/11/2005 from 204.45.234.40
Threat on 02/11/2005 from 58.27.82.161
Threat on 02/12/2005 from 58.27.82.161
Threat on 02/12/2005 from 58.27.82.161
*///:~
