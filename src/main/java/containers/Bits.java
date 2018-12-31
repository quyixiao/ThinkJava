package containers;//: containers/Bits.java
// Demonstration of BitSet.

import java.util.BitSet;
import java.util.Random;

import static net.mindview.util.Print.print;


/**
 *  512 页
 *  如果想要高效的存储大量的 开/关 信息，BigSet 是很好的选择，不过它的效率是对空间而言的
 *  ，如果需要高效的访问时间，BigSet 比本地数组稍慢一些
 *
 *  此外 ，BitSet 的最小容量是long，64位，如果 存储的内容比较少，例如 8 位，那么BitSet 就浪费
 *  了一些空间，因此如果空间对你很重要，最好撰写自己的类，或者直接采用数组来存储你的标志信息，
 *  （只有在创建包含开关信息列表的大量对象，并且促使你做出决定的依据仅仅是性能和他度量因素时，
 *  才属于这种情况，如果你做出这个决定只是因为你认为某些对象太大了，那么你最终会产生不需要的
 *  复杂性，并会浪费掉大量的时间）
 *
 *
 *
 *
 */
public class Bits {
    public static void printBitSet(BitSet b) {
        print("bits: " + b);
        StringBuilder bbits = new StringBuilder();
        for (int j = 0; j < b.size(); j++)
            bbits.append(b.get(j) ? "1" : "0");
        print("bit pattern: " + bbits);
    }

    public static void main(String[] args) {
        Random rand = new Random(47);
        // Take the LSB of nextInt():
        byte bt = (byte) rand.nextInt();
        BitSet bb = new BitSet();
        for (int i = 7; i >= 0; i--)
            if (((1 << i) & bt) != 0)
                bb.set(i);
            else
                bb.clear(i);
        print("byte value: " + bt);

        printBitSet(bb);

        short st = (short) rand.nextInt();
        BitSet bs = new BitSet();
        for (int i = 15; i >= 0; i--)
            if (((1 << i) & st) != 0)
                bs.set(i);
            else
                bs.clear(i);
        print("short value: " + st);
        printBitSet(bs);

        int it = rand.nextInt();
        BitSet bi = new BitSet();
        for (int i = 31; i >= 0; i--)
            if (((1 << i) & it) != 0)
                bi.set(i);
            else
                bi.clear(i);
        print("int value: " + it);
        printBitSet(bi);

        // Test bitsets >= 64 bits:
        BitSet b127 = new BitSet();
        b127.set(127);
        print("set bit 127: " + b127);
        BitSet b255 = new BitSet(65);
        b255.set(255);
        print("set bit 255: " + b255);
        BitSet b1023 = new BitSet(512);
        b1023.set(1023);
        b1023.set(1024);
        print("set bit 1023: " + b1023);
    }
}






/* Output:
byte value: -107
bits: {0, 2, 4, 7}
bit pattern: 1010100100000000000000000000000000000000000000000000000000000000
short value: 1302
bits: {1, 2, 4, 8, 10}
bit pattern: 0110100010100000000000000000000000000000000000000000000000000000
int value: -2014573909
bits: {0, 1, 3, 5, 7, 9, 11, 18, 19, 21, 22, 23, 24, 25, 26, 31}
bit pattern: 1101010101010000001101111110000100000000000000000000000000000000
set bit 127: {127}
set bit 255: {255}
set bit 1023: {1023, 1024}
*///:~
