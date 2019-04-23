package com.java8.dto;


import lombok.Data;

@Data
public class Person {

    private String userName;

    private int age;

    public Person(String userName,int age){
        this.userName = userName;
        this.age = age;
    }


}
