package java8;


import java.util.function.Predicate;


/***
 * Lambda表达式的作用
 *
 * 传递行为，而不仅仅是值
 *  提升抽象层次
 *  api的重用性更加好
 *  更加灵活
 *
 */
public class PredicateTest {

    public static void main(String[] args) {
        Predicate<String> predicate = p -> p.length() > 5;
        System.out.println(predicate.test("13232320"));

    }
}
