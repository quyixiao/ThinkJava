package io;//: io/Blip3.java
// Reconstructing an externalizable object.

import java.io.*;


/***
 * 577 页
 * 因此，为了正常的运行，我们不仅需要在writeExternal() 方法，没有任何默认的行为Externalizable
 * 对象写入，还必须在readexternal（） 方法中恢复数据，起先，可能会有一点迷惑，因为Externalizable
 * 对象的默认的构造行为使其看起来似乎像某种自动发生的存储与恢复的操作，但实际上并非如此。
 *
 *
 */
import static net.mindview.util.Print.print;

public class Blip3 implements Externalizable {
    private int i;
    private String s; // No initialization

    public Blip3() {
        print("Blip3 Constructor");
        // s, i not initialized
    }

    public Blip3(String x, int a) {
        print("Blip3(String x, int a)");
        s = x;
        i = a;
        // s & i initialized only in non-default constructor.
    }

    public String toString() {
        return s + i;
    }

    public void writeExternal(ObjectOutput out)
            throws IOException {
        print("Blip3.writeExternal");
        // You must do this:
        out.writeObject(s);
        out.writeInt(i);
    }

    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {
        print("Blip3.readExternal");
        // You must do this:
        s = (String) in.readObject();
        i = in.readInt();
    }

    public static void main(String[] args)
            throws IOException, ClassNotFoundException {
        print("Constructing objects:");
        Blip3 b3 = new Blip3("A String ", 47);
        print(b3);
        ObjectOutputStream o = new ObjectOutputStream(
                new FileOutputStream("Blip3.out"));
        print("Saving object:");
        o.writeObject(b3);
        o.close();
        // Now get it back:
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("Blip3.out"));
        print("Recovering b3:");
        b3 = (Blip3) in.readObject();
        print(b3);
    }
}


/* Output:
Constructing objects:
Blip3(String x, int a)
A String 47
Saving object:
Blip3.writeExternal
Recovering b3:
Blip3 Constructor
Blip3.readExternal
A String 47
*///:~
