//: typeinfo/E20_ClassDump.java
// {Args: java.lang.String typeinfo.SuperToy}
/****************** Exercise 20 *****************
 * Look up the interface for java.lang.Class in
 * the JDK documentation from java.sun.com.
 * Write a program that takes the name of a class
 * as a command-line argument, then uses the
 * Class methods to dump all the information
 * available for that class. Test your program
 * with a standard library class and a class you
 * create.
 ***********************************************/
package typeinfo;
// The solution is a much-modified version of
// Showmethods.java.

import static net.mindview.util.Print.print;


/******
 *
 *
 * 337页
 *  请从在http://java.sum.com中提供的JDK文档中找出java.lan.class的接口，写一个程序，使它
 *  能够命令行参数所指定的类名称，然后使用Class的方法打印该类所可以获得的
 *  信息，用标准程序的类和你自己写的类，分别测试这个程序
 *
 *
 *  1
 *
 */
public class E20_ClassDump {
    public static void display(String msg, Object[] vals) {
        print(msg);
        for (Object val : vals) {
            print("   " + val);
        }
    }

    public static void
    classInfo(Class<?> c) throws Exception {
        print("c.getName(): " + c.getName());
        print("c.getPackage(): " + c.getPackage());
        print("c.getSuperclass(): " + c.getSuperclass());
        // This produces all the classes declared as members:
        display("c.getDeclaredClasses()",
                c.getDeclaredClasses());
        display("c.getClasses()", c.getClasses());
        display("c.getInterfaces()", c.getInterfaces());
        // The "Declared" version includes all
        // versions, not just public:
        display("c.getDeclaredMethods()",
                c.getDeclaredMethods());
        display("c.getMethods()", c.getMethods());
        display("c.getDeclaredConstructors()",
                c.getDeclaredConstructors());
        display("c.getConstructors()", c.getConstructors());
        display("c.getDeclaredFields()",
                c.getDeclaredFields());
        display("c.getFields()", c.getFields());
        if (c.getSuperclass() != null) {
            print("Base class: --------");
            classInfo(c.getSuperclass());
        }
        print("End of " + c.getName());
    }

    public static void main(String[] args) throws Exception {
        for (String arg : args)
            classInfo(Class.forName(arg));
    }
}