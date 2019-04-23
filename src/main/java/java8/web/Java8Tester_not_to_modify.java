package java8.web;

/***
 *lambda 表达式的局部变量可以不用声明为 final，但是必须不可被后面的代码修改（即隐性的具有final 的语义
 */
public class Java8Tester_not_to_modify {
    public static void main(String args[]) {
        int num = 1;
        Converter<Integer, String> s = (param) -> System.out.println(String.valueOf(param + num));
        s.convert(2);
        /***
         * 把num=5；注释掉就不报错了
         */
        //num = 5;
    }

    public interface Converter<T1, T2> {
        void convert(int i);
    }
}
