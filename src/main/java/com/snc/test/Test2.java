package com.snc.test;


import typeinfo.pets.Person;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Test2 {


    public static void main(String[] args) {
        PersonTest personTest = new PersonTest();
        personTest.setAge(10);
        Map<Object,Object> map = new HashMap<Object,Object>();
        map.put(personTest,"1111");



        PersonTest personTest1 = new PersonTest();
        personTest1.setAge(10);

        map.put(personTest1,"2222");

        System.out.println(map.get(personTest));




        System.out.println(map.get(personTest1));

        Set<PersonTest> personTests = new HashSet<PersonTest>();

        personTests.add(personTest);
        personTests.add(personTest1);
        System.out.println(personTests.size());

    }

}
