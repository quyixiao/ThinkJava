package operators;

import static net.mindview.util.Print.print;


/******
 * 52 页
 * 以一个最高有效位为1的二进制数字开始，提示，使用十六进制常量，用有符号右移操作符对其进行右移，
 * 直至所有的二进制位都被移出为止，每移一位都要使用Interger.toBinaryString()显示结果
 *
 */
public class E11_SignedRightShift {
    public static void main(String[] args) {
        int i = 0x80000000;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
        i >>= 1;
        print(Integer.toBinaryString(i));
    }
}