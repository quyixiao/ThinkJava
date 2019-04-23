package java8.web.stream;

import java.util.Random;

public class Stream_sorted {

    public static void main(String[] args) {
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);
    }
}
