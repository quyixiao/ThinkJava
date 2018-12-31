package com.snc.test;

import java.util.Random;

public class PersonTest {


    private int age ;
    private String name ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        return   new Random().nextInt(100);
    }


    @Override
    public boolean equals(Object obj) {

        System.out.println(" this.hashCode() :" +  this.hashCode() + " obj.hashCode() :" + obj.hashCode());
        //return this.hashCode()==(obj.hashCode());
        return this.age == ((PersonTest)obj).age;
    }
}
