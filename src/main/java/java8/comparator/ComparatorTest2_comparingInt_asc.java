package java8.comparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorTest2_comparingInt_asc {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("nihao", "hello", "world", "welcome");
        Collections.sort(list, Comparator.comparingInt(String::length));
        System.out.println(list);

    }
}
