
//: generics/E21_ClassTypeCapture2.java
/****************** Exercise 21 *****************
 * Modify ClassTypeCapture.java by adding a
 * Map<String,Class<?>>, a method
 * addType(String typename, Class<?> kind), and a
 * method createNew(String typename).createNew()
 * will either produce a new instance of the class
 * associated with its argument string, or produce
 * an error message.
 ************************************************/
package generics;

import java.util.HashMap;
import java.util.Map;

import static net.mindview.util.Print.print;


/****
 *
 *
 * 381页
 * 修改ClassTypeCapture.java，添加一个Map<String,Class<?>> ，一个addtype(String,typename,Class<？> 方法和一个CreateNew(String,Type
 *)),方法，CreateNew()将产生一个与其参数字符串相关联的类的新实例，或者产生一条错误消息
 *
 *
 * 1
 */
class ClassTypeCapture2 {
    Map<String, Class<?>> types =
            new HashMap<String, Class<?>>();

    public Object createNew(String typename) {
        Class<?> cl = types.get(typename);
        try {
            return cl.newInstance();
        } catch (NullPointerException e) {
            print("Not a registered typename: " + typename);
        } catch (Exception e) {
            print(e.toString());
        }
        return null;
    }

    public void addType(String typename, Class<?> kind) {
        types.put(typename, kind);
    }
}

public class E21_ClassTypeCapture2 {
    public static void main(String[] args) {
        ClassTypeCapture2 ctt = new ClassTypeCapture2();
        ctt.addType("Building", Building.class);
        ctt.addType("House", House.class);
        ctt.addType("Product", Product.class);
        print(ctt.createNew("Building").getClass());
        print(ctt.createNew("House").getClass());
        ctt.createNew("Product");
        ctt.createNew("Car_override_print");
    }
}