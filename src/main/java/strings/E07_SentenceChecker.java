//: strings/E07_SentenceChecker.java
/****************** Exercise 7 ******************
 * Using the documentation for java.util.regex.Pattern
 * as a resource, write and test a regular expression
 * that checks a sentence to see that it begins with a
 * capital letter and ends with a period.
 ************************************************/
package strings;

/*******
 *
 *
 * 297页
 *      请参考java.util.regex.Pattern的文档，编写一个正则表达式，检查一个句子是否以
 *  大写字母开头，以句号结尾
 *
 *
 * 1
 */
public class E07_SentenceChecker {
    public static boolean matches(String text) {
        return text.matches("\\p{javaUpperCase}.*\\.");
    }

    public static void main(String[] args) {
        System.out.println(matches("This is correct."));
        System.out.println(matches("bad sentence 1."));
        System.out.println(matches("Bad sentence 2"));
        System.out.println(matches("This is also correct..."));
    }
}