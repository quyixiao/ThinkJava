package io;//: io/SerialCtl.java
// Controlling serialization by adding your own
// writeObject() and readObject() methods.

import java.io.*;


/**
 * 580页
 * 在这个例子中，有一个String字段是普通字段，面另一个是transient字段，用来证明非transient 字段由defaultWriteObject()
 * 方法保存，而transient字段在程序中明确保存和恢复，字段是定义在构造器内部而不是在定义处进行初始化的，以此可以证明它们在反
 * 序列化还原期间没有被一些自动化机制初始化
 *
 *  如果我们打算使用默认的机制写入对象的非transient部分，那么必须调用defaultWriteObject() 作为WriteObject() 中的第一个
 *  操作，并让defaultReadObject()作为readObject() 中的第一个操作，这些都是奇怪的方法调用，例如 ，如果我们正在为ObjectOut
 *  putstream 调用defaultWriteObject() 且没有传递任何参数，然而不知道何故它却可以运行，并且知道对象的引用以及如何写入非
 *  transient部分，真是奇怪之极
 *
 *  对transient对象存储和恢复使用了我们比较熟悉的代码，请再考虑一下这里所发生的事情，在main() 中，创建SerialCtrl对象，然后
 *  将其序列化ObjectOutPutStream 注意在这种情况下，使用的是缓冲区而不是文件，这对于ObjectOutputStream 来说是完全一样的
 *  序列化发生在代码中，
 *  o.riteObject(sc)
 *
 *
 *  writeObject() 方法必须检查sc,判断它是否拥有自己的writeObject()方法，不是检查接口，这里根本就没有接口，也不是检查类的类型
 *  ，而是利用反射来真正方法必需检查类的类型，而是利用反射来真正的搜索方法，如果有，那么，就会使用它，对readObject()也采用了它，
 *  对tranient对象的存储
 *
 *  这里根本就没有接口，也不是检查类的类型，而是利用反射来真正的搜索问题，如果有的话，那么就会使用它，对readObject() 也采用了类似的
 *  方法，或许这是解决这个问题的唯一切实可行的方法，但它确实有点古怪
 *
 *     我们会发现jdk 文档中有许多的注解是从下面的文字开始
 *
 *     警告： 该类的序列化对象和未来swing 版本不兼容，当前对序列化的支持适用于短期存储或应用之间的RMI
 *
 *
 *  这是因为java版本控制机制过于简单，因而不能在任何场合都可靠运转，尤其是对javaBeans更是如此，有关人员正在设法修正
 *  这一设计，也就是警告中的相关部分
 *
 *  一个比较
 *
 *
 *
 *
 */
public class SerialCtl implements Serializable {
    private String a;
    private transient String b;

    public SerialCtl(String aa, String bb) {
        a = "Not Transient: " + aa;
        b = "Transient: " + bb;
    }

    public String toString() {
        return a + "\n" + b;
    }

    private void writeObject(ObjectOutputStream stream)
            throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(b);
    }

    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        b = (String) stream.readObject();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerialCtl sc = new SerialCtl("Test1", "Test2");
        System.out.println("Before:\n" + sc);

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(buf);
        o.writeObject(sc);
        // Now get it back:
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buf.toByteArray()));


        SerialCtl sc2 = (SerialCtl) in.readObject();

        System.out.println("After:\n" + sc2);
    }
}



/* Output:
Before:
Not Transient: Test1
Transient: Test2
After:
Not Transient: Test1
Transient: Test2
*///:~
