package containers;//: containers/CanonicalMapping.java
// Demonstrates WeakHashMap.

import java.util.WeakHashMap;


/**
 * 520 页
 *
 * 运行此程序，会看到垃圾回收器每隔三个键就跳过一个，因为指向那个键的普通引用被存
 * 入了keys 数组，所以那些对象不能被垃圾回收器回收
 *
 *
 *
 *  Vector 和Enumeration
 * Vector 是唯一一个自我扩展的序列，所以它被大量的使用，它的缺点多到这里都难以描述
 * 基本上，可将其看作AarrayList ,但是具有又长又难记的方法名很多，在订正过的java容器
 * 类库中，Verctor 被改造过的，可将其归类为Collection 和List ，这样做有点不妥当的
 * ，可能会让人误会Vector 变得好用了，实际上，这样做只是为了支持java2 之前的代码
 *
 *
 *
 *
 *
 */
class Element {
    private String ident;

    public Element(String id) {
        ident = id;
    }

    public String toString() {
        return ident;
    }

    public int hashCode() {
        return ident.hashCode();
    }

    public boolean equals(Object r) {
        return r instanceof Element &&
                ident.equals(((Element) r).ident);
    }

    protected void finalize() {
        System.out.println("Finalizing " +
                getClass().getSimpleName() + " " + ident);
    }
}

class Key extends Element {
    public Key(String id) {
        super(id);
    }
}

class Value extends Element {
    public Value(String id) {
        super(id);
    }
}

public class CanonicalMapping {
    public static void main(String[] args) {
        int size = 1000;
        // Or, choose size via the command line:
        if (args.length > 0)
            size = new Integer(args[0]);
        Key[] keys = new Key[size];
        WeakHashMap<Key, Value> map = new WeakHashMap<Key, Value>();
        for (int i = 0; i < size; i++) {
            Key k = new Key(Integer.toString(i));
            Value v = new Value(Integer.toString(i));
            if (i % 3 == 0)
                keys[i] = k; // Save as "real" references
            map.put(k, v);

        }
        System.gc();
    }
} /* (Execute to see output) *///:~
