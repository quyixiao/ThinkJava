package java8;

import java.util.function.Function;


/****
 *
 *
 */
public class FunctionTest2 {


    public static void main(String[] args) {
        FunctionTest2 test = new FunctionTest2();
        int a = 2;

        System.out.println(test.compose(a, value -> value * 3, value -> value * value));
        System.out.println(test.andThen(a, value -> value * 3, value -> value * value));


    }


    /**
     * 先执行第二方法
     *
     * @param a
     * @param function1
     * @param function2
     * @return
     */
    public int compose(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {

        return function1.compose(function2).compose(function2).apply(a);
    }


    /***
     * 先执行第一个方法 36
     * @param a
     * @param function1
     * @param function2
     * @return
     */
    public int andThen(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {

        return function1.andThen(function2).apply(a);
    }


}
