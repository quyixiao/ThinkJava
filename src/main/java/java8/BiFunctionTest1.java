package java8;

import java.util.function.BiFunction;
import java.util.function.Function;


/***
 * 实现两个参数的加，减，乘，除
 */
public class BiFunctionTest1 {

    public static void main(String[] args) {
        BiFunctionTest1 test = new BiFunctionTest1();
        int a = 4;
        int b = 2;

        System.out.println(test.compute3(a, b, (value1, value2) -> value1 + value2));
        System.out.println(test.compute3(a, b, (value1, value2) -> value1 - value2));
        System.out.println(test.compute3(a, b, (value1, value2) -> value1 * value2));
        System.out.println(test.compute3(a, b, (value1, value2) -> value1 / value2));


        System.out.println(test.compute4(a, b, (value1, value2) -> value1 / value2, value -> value * 3));
    }


    public int compute3(int a, int b, BiFunction<Integer, Integer, Integer> biFunction) {
        return biFunction.apply(a, b);
    }


    /***
     * 只能用 andThen 和fuction,因为第一个参数返回的值，作为function的入参
     * @return
     */
    public int compute4(int a, int b, BiFunction<Integer, Integer, Integer> biFuction, Function<Integer, Integer> function) {
        return biFuction.apply(a, b);
    }


}
