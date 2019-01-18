package io;//: io/GetData.java
// Getting different representations from a ByteBuffer

import java.nio.ByteBuffer;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;


/**
 * 在分配一个Bytebuffer 之后，可以通过检测它的值来查看缓冲器的方式是否将其内容自动置为零，
 *
 *
 *
 * 尽管ByteBuffer只能保存字节类型的数据，但是它具有可以从其所容纳的字节中产生出各种
 * 不同基本类型值的方法，下面这个例子展示了怎样使用这些方法来插入和抽取各种数值
 *
 *
 *
 * 556 页
 *
 *
 *
 *
 */
public class GetData {
    private static final int BSIZE = 1024;

    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);
        // Allocation automatically zeroes the ByteBuffer:
        int i = 0;
        while (i++ < bb.limit())
            if (bb.get() != 0)
                print("nonzero");
        print("i = " + i);
        bb.rewind();
        // Store and read a char array:
        bb.asCharBuffer().put("Howdy!");
        char c;
        while ((c = bb.getChar()) != 0)
            printnb(c + " ");
        print();
        bb.rewind();
        // Store and read a short:
        bb.asShortBuffer().put((short) 471142);
        print(bb.getShort());
        bb.rewind();
        // Store and read an int:
        bb.asIntBuffer().put(99471142);
        print(bb.getInt());
        bb.rewind();
        // Store and read a long:
        bb.asLongBuffer().put(99471142);
        print(bb.getLong());
        bb.rewind();
        // Store and read a float:
        bb.asFloatBuffer().put(99471142);
        print(bb.getFloat());
        bb.rewind();
        // Store and read a double:
        bb.asDoubleBuffer().put(99471142);
        print(bb.getDouble());
        bb.rewind();
    }
} /* Output:
i = 1025
H o w d y !
12390
99471142
99471142
9.9471144E7
9.9471142E7
*///:~
