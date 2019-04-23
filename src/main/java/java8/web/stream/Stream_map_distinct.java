package java8.web.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Stream_map_distinct {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        squaresList.forEach(System.out::println);
    }
}
