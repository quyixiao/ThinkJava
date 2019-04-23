package java8;

import java.util.Comparator;
import java.util.function.BinaryOperator;

public class BinaryOperatorTest {

    public static void main(String[] args) {
        BinaryOperatorTest test = new BinaryOperatorTest();
        int a = 10;
        int b = 2;
        System.out.println(test.compute(a, b, (c, d) -> c + d));
        System.out.println(test.compute(a, b, (c, d) -> c - d));
        System.out.println(test.compute(a, b, (c, d) -> c * d));
        System.out.println(test.compute(a, b, (c, d) -> c / d));

        //查找长度比较小的字符串
        System.out.println(test.getShort("abc", "defa", (c, d) -> c.length() - d.length()));
        System.out.println(test.getMax("abc", "defa", (c, d) -> c.length() - d.length()));

        //首字母比较小的
        System.out.println(test.getShort("abc", "defa", (c, d) -> c.charAt(0) - d.charAt(0)));
    }

    public static int compute(int a, int b, BinaryOperator<Integer> binaryOperator) {
        return binaryOperator.apply(a, b);
    }


    public static String getShort(String a, String b, Comparator<String> comparator) {
        return BinaryOperator.minBy(comparator).apply(a, b);

    }

    public static String getMax(String a, String b, Comparator<String> comparator) {
        return BinaryOperator.maxBy(comparator).apply(a, b);
    }


}
