//: typeinfo/E18_ShowMethods3.java
// {Args: typeinfo.E18_ShowMethods3}
/********************** Exercise 18 **********************
 * Make ShowMethods a non-public class and verify that
 * the synthesized default constructor no longer appears in
 * the output.
 *********************************************************/
package typeinfo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

import static net.mindview.util.Print.print;


/*****
 *
 * 337页
 *  将ShowMethods变为一个非public的类，并验证合成的默认构造器不会再在输出中出现
 *
 */
class E18_ShowMethods3 {
    private static String usage =
            "usage:\n" +
                    "E18_ShowMethods3 qualified.class.name\n" +
                    "To show all methods in class or:\n" +
                    "E18_ShowMethods3 qualified.class.name word\n" +
                    "To search for methods involving 'word'";
    private static Pattern p = Pattern.compile("\\w+\\.");

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