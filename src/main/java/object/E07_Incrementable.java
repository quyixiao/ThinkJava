package object;


/*****
 * 37页
 *
 * 将Incremetnttable的代码段改写面一个完整的可运行程序
 *
 */
class StaticTest {
    static int i = 47;
}

public class E07_Incrementable {
    static void increment() {
        StaticTest.i++;
    }

    public static void main(String[] args) {

        /****
         * 加了三次
         */
        E07_Incrementable sf = new E07_Incrementable();
        sf.increment();
        E07_Incrementable.increment();
        increment();
        System.out.println(StaticTest.i);
    }
}