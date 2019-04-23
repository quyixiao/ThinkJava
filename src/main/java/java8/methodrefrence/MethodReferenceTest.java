package java8.methodrefrence;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/***
 * compare是一个函数式接口
 *
 *
 * 方法引用本质上方法引用看作是一个函数指针 ，function pointer
 *  方法引用是指向另外一个函数的指针
 *
 *
 *  方法引用共分为4类：
 *      有一个容易理解 ，另一个是不容易理解的
 *      1.类名::静态方法名(是jdk8中的新的方法引用)
 *      2.引用名（对象名）::实例方法名
 *      3.类的名字，实例方法名
 *      4.构造方法引用: 类名::new
 *
 *
 *
 *
 */

public class MethodReferenceTest {


    public String getString(Supplier<String> supplier) {
        return supplier.get() + "test";
    }


    public String getString2(String str, Function<String, String> function) {
        return function.apply(str);
    }


    public static void main(String[] args) {
        Student student1 = new Student("zhangsan", 10);
        Student student2 = new Student("lisi", 90);
        Student student3 = new Student("wangwu", 50);
        Student student4 = new Student("zhaoliu", 40);
        List<Student> students = Arrays.asList(student1, student2, student3, student4);


        //传统的使用lambda表达式的方式来进行排序
        //students.sort((studentParam1,studentParam2) -> Student.compareStudentByScore(studentParam1,studentParam2));
        students.sort((studentParam1, studentParam2) -> Student.compareStudentByName(studentParam1, studentParam2));


        //使用方法引用的文来排序
        //students.sort(Student::compareStudentByScore);
        students.sort(Student::compareStudentByName);

        //这种方法引用的方式和上面的lambda表达式的方式是一样的，方法引用在某些时候是有用的
        System.out.println(students);

        //lambda
        StudentComparator comparator = new StudentComparator();
        students.sort((studentParam1, studentParam2) -> comparator.compareStudentByScore(studentParam1, studentParam2));
        students.forEach(student -> System.out.println(student.getScore()));

        //根据分数排序
        students.sort(comparator::compareStudentByScore);
        students.forEach(student -> System.out.println(student.getScore()));
        //根据名字排序
        students.sort(comparator::compareStudentByName);
        students.forEach(student -> System.out.println(student.getName()));

        //lambda表达式的第一个参数来调用他
        students.sort(Student::compareByName);

        //第二个参数

        System.out.println(students);

        List<String> cities = Arrays.asList("qingdao", "chongqing", "tianjin", "beijing");

        Collections.sort(cities, (city1, city2) -> city1.compareToIgnoreCase(city2));
        System.out.println(cities);
        cities.forEach(item -> System.out.print(item + " , "));

        System.out.println("============================================");

        MethodReferenceTest test = new MethodReferenceTest();
        System.out.println(test.getString(String::new));

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(test.getString2("quyixiao", String::new));


    }

}
