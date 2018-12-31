package control;

import java.util.Random;

/****
 * 66页
 *  写一个程序，产生25个int类型的随机数，对于每一个随机值，使用if-else语句来将其分类为大于，小于，或等于
 *  紧随它而随机第一页的值
 *1
 *
 */
public class E02_RandomInts {
    static Random r = new Random(47);

    public static void compareRand() {
        int a = r.nextInt();
        int b = r.nextInt();
        System.out.println("a = " + a + ", b = " + b);
        if (a < b)
            System.out.println("a < b");
        else if (a > b)
            System.out.println("a > b");
        else
            System.out.println("a = b");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 25; i++)
            compareRand();
    }
} 