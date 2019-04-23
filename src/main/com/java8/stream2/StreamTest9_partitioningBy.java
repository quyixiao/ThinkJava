package com.java8.stream2;

import com.java8.dto.Student;
import com.sun.xml.internal.xsom.impl.scd.Iterators;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;

/***
 * 开始执行
 *
 *
 *  1.collect:收集器
 *  2.Collector作为一个collect方法的参数
 *  3.Collector是一个可变的汇聚操作，将输入元素累积到一个可变的结果容器中，它会在所有的元素都处理完毕后，将累积的结果转换为一个最终的表示（这是一个可选的操作）
 *  它支持串行与并行的两种方式执行
 *  4.Collectors本身提供了关于Collector常见的汇聚实现，Collocotrs本身实际上是一个工厂
 *  5.为了确保串行与并行操作结果的等价性，Collector函数需要满足两个条件：Identity(同一性)与Associativity（结合性）
 *  6.a == combiner.apply(a,supplier.get());
 *  7.函数式编程最大的特点：表示做什么，而不是如果做
 *
 *
 *
 *
 *
 *
 *
 *  (List<String> list1 ,List<String> list2 ) -> {list1.addAll(list2);
 *  return list1;} 空的结果容器
 *
 *
 *
 *  combiner 函数：并行流有4个线程去同时执行那么生成4个部分结果。
 *
 *  1，2，3，4
 *  1，2 -> 5
 *  5，3 -> 6
 *  6,4 -> 7
 *
 *
 * 将一个参数的状态折叠到另一个* 1 ,2 ->*
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class StreamTest9_partitioningBy {

    public static void main(String[] args) {
        //一个reduction串行实现
        Student student1 = new Student("zhangsan", 80);
        Student student2 = new Student("lisi", 90);
        Student student3 = new Student("wangwu", 100);
        Student student4 = new Student("zhaoliu", 90);
        Student student5 = new Student("zhaoliu", 90);
        List<Student> students = Arrays.asList(student1, student2, student3, student4,student5);

        Map<Boolean,List<Student>> map = students.stream().collect(partitioningBy(student->student.getAge() > 80));
        System.out.println(map);


    }
}
