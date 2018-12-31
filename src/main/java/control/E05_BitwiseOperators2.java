package control;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;


/****
 * 67页
 *  重复第三章的练习10，不要用integer.toBinaryString()方法，而是用三元操作符和的定位操作符来显示
 *  二进制的1和0
 *
 * 1
 */
public class E05_BitwiseOperators2 {
    private static void toBinaryString(int i) {
        char[] buffer = new char[32];
        int bufferPosition = 32;
        do {
            buffer[--bufferPosition] =
                    ((i & 0x01) != 0) ? '1' : '0';
            i >>>= 1;
        } while (i != 0);
        for (int j = bufferPosition; j < 32; j++)
            printnb(buffer[j]);
        print();
    }

    public static void main(String[] args) {
        int i1 = 0xaaaaaaaa;
        int i2 = 0x55555555;
        printnb("i1 = ");
        toBinaryString(i1);
        printnb("i2 = ");
        toBinaryString(i2);
        printnb("~i1 = ");
        toBinaryString(~i1);
        printnb("~i2 = ");
        toBinaryString(~i2);
        printnb("i1 & i1 = ");
        toBinaryString(i1 & i1);
        printnb("i1 | i1 = ");
        toBinaryString(i1 | i1);
        printnb("i1 ^ i1 = ");
        toBinaryString(i1 ^ i1);
        printnb("i1 & i2 = ");
        toBinaryString(i1 & i2);
        printnb("i1 | i2 = ");
        toBinaryString(i1 | i2);
        printnb("i1 ^ i2 = ");
        toBinaryString(i1 ^ i2);
    }
} 