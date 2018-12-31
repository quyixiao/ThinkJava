package operators;

import static net.mindview.util.Print.print;


/*****
 *
 * 52页
 *
 * 编写一个方法，它以二进制形式显示char类型的值，使用多个不同的字符来展示它
 *
 */
public class E13_BinaryChar {
    public static void main(String[] args) {
        print("A: " + Integer.toBinaryString('A'));
        print("!: " + Integer.toBinaryString('!'));
        print("x: " + Integer.toBinaryString('x'));
        print("7: " + Integer.toBinaryString('7'));
    }
}