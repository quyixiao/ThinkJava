package java8.web;


/****
 * 在Lambda 表达式当中不允许声明一个与局部变量同名的参数或者局部变量
 */
public class Java8Tester_variable_name {
    public static void main(String args[]) {
        // String first = "";
        //把String first = "";注掉就不报错了
        Comparator<String> comparator = (first, second) -> System.out.println(Integer.compare(first.length(), second.length()));//编译会出错

        comparator.com("aaaaa", "bb");
    }

    public interface Comparator<T> {
        void com(String a, String b);
    }
}
