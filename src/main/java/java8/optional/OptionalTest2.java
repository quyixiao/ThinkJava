package java8.optional;

import java8.dto.Company;
import java8.dto.Employee;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


/***
 * 函数式接口
 * 函数式接口是只包含一个抽象方法声明的接口
 * java.lang.Runnable就是一个函数式接口，在Runnable接口中只声明了一个方法void run()
 * 每个Lambda表达式都能隐式的赋值给函数式接口
 *
 * 方法引用：method refrence
 *
 */
public class OptionalTest2 {


    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setName("zhangsan");


        Employee employee1 = new Employee();
        employee1.setName("lisi");

        Company company = new Company();
        company.setName("霖梓");

        List<Employee> employees = Arrays.asList(employee, employee1);

        company.setEmployees(employees);


        List<Employee> list = company.getEmployees();


        Optional<Company> optional = Optional.ofNullable(company);

        System.out.println(optional.map(theCompnay -> theCompnay.getEmployees())
                .orElse(Collections.emptyList()));


    }


}
