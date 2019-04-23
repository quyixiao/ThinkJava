package java8.dto;

import lombok.Data;

import java.util.List;


@Data
public class Company {

    private String name;

    private List<Employee> employees;

}
