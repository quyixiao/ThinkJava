package java8;

import java8.dto.Student;

import java.util.function.Supplier;

public class StudentTest {

    public static void main(String[] args) {
        Supplier<Student> supplier = () -> new Student();
        System.out.println(supplier.get().getName());
        System.out.println("=================================");
        //构造方法引用
        Supplier<Student> supplier1 = Student::new;
        System.out.println(supplier1.get().getName());


    }
}
