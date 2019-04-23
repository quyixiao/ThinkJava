package com.java8.stream;

import com.alibaba.fastjson.JSON;
import com.java8.dto.MyStudent;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/***
 * select * from student group by name;
 *
 *
 * 1.循环列表
 * 2.取出学生的名字
 * 3.检查map中是否存在该名字，不存在则直接添加到该map用户中，存在则将map中的List对象取出来，然后将该Student对象添加到List中
 * 4.返回map对象
 *
 * 和sql中的分组一样的哈
 *
 *
 */

public class StreamTest16_GroupingBy {

    public static void main(String[] args) {
        MyStudent student1 = new MyStudent("zhangsan", 100, 20);
        MyStudent student2 = new MyStudent("lisi", 90, 20);
        MyStudent student3 = new MyStudent("wangwu", 90, 30);
        MyStudent student4 = new MyStudent("zhaoliu", 80, 40);
        MyStudent student5 = new MyStudent("zhaoliu", 30, 20);


        List<MyStudent> myStudents = Arrays.asList(student1, student2, student3, student4,student5);

        Map<String,List<MyStudent>> map =  myStudents.stream().collect(Collectors.groupingBy(MyStudent::getName));

        System.out.println(JSON.toJSONString(map));


        System.out.println("=====================================");

        Map<Integer,List<MyStudent>> map1 =  myStudents.stream().collect(Collectors.groupingBy(MyStudent::getScore));

        System.out.println(JSON.toJSONString(map1));




    }
}
