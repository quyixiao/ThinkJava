package operators;

import static net.mindview.util.Print.print;


/****8
 * 49页
 * 编写一个具有两个常量值的程序，一个具有交替的二进制位1和0 ，其中最低有效位为0
 * 另一个也具有交替的二进制位1和0 ，但是其最低有效位为1，提示，使用十六进制常量来表示是
 * 最简的方法，取这两个值，用按位操作符以所有可能的方式结合运算它们，然后用Integer.toBinaryString()显示
 *
 *
 *
 * 1
 *
 *
 * 1
 */
public class E10_BitwiseOperators {
    public static void main(String[] args) {
        int i1 = 0xaaaaaaaa;
        int i2 = 0x55555555;
        print("i1 = " + Integer.toBinaryString(i1));
        print("i2 = " + Integer.toBinaryString(i2));
        print("~i1 = " + Integer.toBinaryString(~i1));
        print("~i2 = " + Integer.toBinaryString(~i2));
        print("i1 & i1 = " + Integer.toBinaryString(i1 & i1));
        print("i1 | i1 = " + Integer.toBinaryString(i1 | i1));
        print("i1 ^ i1 = " + Integer.toBinaryString(i1 ^ i1));
        print("i1 & i2 = " + Integer.toBinaryString(i1 & i2));
        print("i1 | i2 = " + Integer.toBinaryString(i1 | i2));
        print("i1 ^ i2 = " + Integer.toBinaryString(i1 ^ i2));
    }
}