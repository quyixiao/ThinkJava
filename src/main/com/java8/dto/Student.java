package com.java8.dto;

import lombok.Data;

@Data
public class Student {
    private String name = "zhangsan";

    private int age = 20;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
