//: generics/E24_FactoryCapture.java
/****************** Exercise 23 *****************
 * Modify Exercise 21 so that factory objects are
 * held in the Map instead of Class<?>.
 ************************************************/
package generics;

import java.util.HashMap;
import java.util.Map;

import static net.mindview.util.Print.print;


/*****
 *
 *
 *
 * 383页
 *  修改练习21,使得工厂对象是由一个Map而不是Class<?>持有的
 *
 */
class FactoryCapture {
    Map<String, FactoryI1<?>> factories =
            new HashMap<String, FactoryI1<?>>();

    public Object createNew(String factoryname, int arg) {
        FactoryI1<?> f = factories.get(factoryname);
        try {
            return f.create(arg);
        } catch (NullPointerException e) {
            print("Not a registered factoryname: " + factoryname);
            return null;
        }
    }

    public void
    addFactory(String factoryname, FactoryI1<?> factory) {
        factories.put(factoryname, factory);
    }
}

public class E24_FactoryCapture {
    public static void main(String[] args) {
        FactoryCapture fc = new FactoryCapture();
        fc.addFactory("Integer", new IntegerFactory1());
        fc.addFactory("Widget", new Widget1.Factory());
        print(fc.createNew("Integer", 1));
        print(fc.createNew("Widget", 2));
        fc.createNew("Product", 3);
    }
}