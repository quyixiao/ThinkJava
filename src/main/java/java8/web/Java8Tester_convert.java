package java8.web;

public class Java8Tester_convert {
    public static void main(String args[]) {
        final int num = 1;
        Converter<Integer, String> s = (param) -> System.out.println(String.valueOf(param + num));
        s.convert(2); // 输出结果为 3
    }

    public interface Converter<T1, T2> {
        void convert(int i);
    }
}
