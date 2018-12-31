package io;//: io/Endians.java
// Endian differences and data storage.

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import static net.mindview.util.Print.print;


/**
 * 560 页
 *
 * capacity()           返回缓冲区容量
 * clear()              清空缓冲区，将position设置为0 ，limit 设置为容量，我们可以调用些方法覆写缓冲区
 * flip()               将limit设置为position，position 设置为0 ，此方法用于准备从缓冲区读取己经写入的数据
 * limit()
 *
 */
public class Endians {
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[12]);
        bb.asCharBuffer().put("abcdef");
        print(Arrays.toString(bb.array()));
        bb.rewind();
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.asCharBuffer().put("abcdef");
        print(Arrays.toString(bb.array()));
        bb.rewind();
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.asCharBuffer().put("abcdef");
        print(Arrays.toString(bb.array()));
    }
} /* Output:
[0, 97, 0, 98, 0, 99, 0, 100, 0, 101, 0, 102]
[0, 97, 0, 98, 0, 99, 0, 100, 0, 101, 0, 102]
[97, 0, 98, 0, 99, 0, 100, 0, 101, 0, 102, 0]
*///:~
