//: typeinfo/E17_ShowMethods2.java
// {Args: typeinfo.E17_ShowMethods2}
/********************** Exercise 17 **********************
 * Modify the regular expression in ShowMethods.java to
 * also strip off the keywords native and final.
 * (Hint: Use the OR operator '|'.)
 *********************************************************/
package typeinfo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

import static net.mindview.util.Print.print;


/******
 *
 * 337页
 *  修改showMethods.java中的正则表达式，以去掉native和final关键字(提示：使用或运算符 | )
 */
public class E17_ShowMethods2 {
    private static String usage =
            "usage:\n" +
                    "E17_ShowMethods2 qualified.class.name\n" +
                    "To show all methods in class or:\n" +
                    "E17_ShowMethods2 qualified.class.name word\n" +
                    "To search for methods involving 'word'";
    private static Pattern p =
            Pattern.compile("\\w+\\.|native\\s|final\\s");

    public static void main(String[] args) {
        if (args.length < 1) {
            print(usage);
            System.exit(0);
        }
        int lines = 0;
        try {
            Class<?> c = Class.forName(args[0]);
            Method[] methods = c.getMethods();
            Constructor<?>[] ctors = c.getConstructors();
            if (args.length == 1) {
                for (Method method : methods)
                    print(
                            p.matcher(method.toString()).replaceAll(""));
                for (Constructor<?> ctor : ctors)
                    print(p.matcher(ctor.toString()).replaceAll(""));
                lines = methods.length + ctors.length;
            } else {
                for (Method method : methods)
                    if (method.toString().indexOf(args[1]) != -1) {
                        print(
                                p.matcher(method.toString()).replaceAll(""));
                        lines++;
                    }
                for (Constructor<?> ctor : ctors)
                    if (ctor.toString().indexOf(args[1]) != -1) {
                        print(p.matcher(
                                ctor.toString()).replaceAll(""));
                        lines++;
                    }
            }
        } catch (ClassNotFoundException e) {
            print("No such class: " + e);
        }
    }
}