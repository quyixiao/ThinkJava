package operators;

import static net.mindview.util.Print.print;


/****
 *  52 页
 *  以一个所有位都为1的二进制数字开始，先左移它，然后用无符号右移操作符对其人进行右移操作，直至
 *  所有的二进制位都被移出为止，每移一位都要使用Integer.toBinaryString()显示结果
 *
 */
public class E12_UnsignedRightShift {
    public static void main(String[] args) {
        int i = -1 << 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
        i >>>= 1;
        print(Integer.toBinaryString(i));
    }
}