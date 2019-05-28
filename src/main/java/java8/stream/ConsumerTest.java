package java8.stream;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class ConsumerTest {


    public void test(Consumer<Integer> consumer) {
        consumer.accept(100);
    }

    public static void main(String[] args) {
        ConsumerTest consumerTest = new ConsumerTest();

        Consumer<Integer> consumer = i -> System.out.println(i);
        IntConsumer intConsumer = i -> System.out.println(i);
        System.out.println("consumer=" + (consumer instanceof Consumer));
        //Exception in thread "main" java.lang.ClassCastException: java8.stream.ConsumerTest$$Lambda$2/664223387 cannot be cast to java.util.function.Consumer
        System.out.println("intConsumer=" + (intConsumer instanceof Consumer));
        //consumerTest.test((Consumer)intConsumer);

        consumerTest.test(consumer);                        //面向对象的方式传递

        consumerTest.test(consumer::accept);                //函数式的传递方式

        consumerTest.test(intConsumer::accept);             //函数式的传递方式


    }
}






























