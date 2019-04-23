package java8.web.stream;

import java.util.Random;


/***
 * limit 方法用于获取指定数量的流。以下代码片段使用 limit 方法打印出 10 条数据
 */
public class StreamLimit {


    public static void main(String[] args) {
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
    }
}
