package com.java8.stream;


import com.alibaba.fastjson.JSON;
import com.java8.dto.MyStudent;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/***
 *
 * select name ,count(*) from abc;
 *
 *
 */
public class StreamTest17_Counting {

    public static void main(String[] args) {

        MyStudent student1 = new MyStudent("zhangsan", 100, 20);
        MyStudent student2 = new MyStudent("lisi", 90, 20);
        MyStudent student3 = new MyStudent("wangwu", 90, 30);
        MyStudent student4 = new MyStudent("zhaoliu", 80, 40);
        MyStudent student5 = new MyStudent("zhaoliu", 30, 20);

        List<MyStudent> myStudents = Arrays.asList(student1, student2, student3, student4, student5);
        Map<String, Long> map = myStudents.stream().collect(Collectors.groupingBy(MyStudent::getName, Collectors.counting()));
        System.out.println(JSON.toJSONString(map));


    }


}
