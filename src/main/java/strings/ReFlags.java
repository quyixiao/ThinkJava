package strings;//: strings/ReFlags.java

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/****
 * 305页
 *
 *
 *      在这个例子中，我们创建了一个模式，它将匹配所有的以java ，Java,JAVA等开头的行
 *  并且是在设置了多行标记的状态下，从字符序列的第一个字符开始，至每一个行终结符，都进行匹配
 *  ，注意，Group（）方法只返回已匹配的部分
 *
 * *
 *
 */
public class ReFlags {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("^java", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        Matcher m = p.matcher(
                "java has regex\nJava has regex\n" +
                        "JAVA has pretty good regular expressions\n" +
                        "Regular expressions are in Java");
        while (m.find())
            System.out.println(m.group());
    }
} /* Output:
java
Java
JAVA
*///:~
