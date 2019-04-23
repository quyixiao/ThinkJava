package java8.web.stream;

import java.util.Arrays;
import java.util.List;

public class Stream_filter_count {


    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
// 获取空字符串的数量
        int count = (int) strings.stream().filter(string -> string.isEmpty()).count();

        System.out.println(count);
    }
}
