package com.mysql.generator;

import java.util.*;
import java.util.stream.Collectors;
 
public class Test {
    public static void main(String[] args) {
		List<Student> list = new ArrayList<>();
		Student student1 = new Student();student1.setAge("12");student1.setSex(0);
		Student student2 = new Student();student2.setAge("13");student2.setSex(2);
		Student student3 = new Student();student3.setAge("11");student3.setSex(1);
		Student student4 = new Student();student4.setAge("18");student4.setSex(1);
		Student student5 = new Student();student5.setAge("18");student5.setSex(0);
		Student student6 = new Student();student6.setAge("18");student6.setSex(2);
		Student student7 = new Student();student7.setAge("18");student7.setSex(2);



		list.add(student1);
		list.add(student2);
		list.add(student3);
		list.add(student4);
		list.add(student5);
		list.add(student6);
		list.add(student7);
		List<Demo> demos = new ArrayList<Demo>();
		
		// 原始数据
		System.out.println("原始数据 组装list<demo>*******************");
		demos = list.stream().map(student -> new Demo(student.getAge(), student.getSex())).collect(Collectors.toList());
		demos.forEach(demo -> {
			System.out.println("年龄 " + demo.getAge() + "  性别 " + demo.getSex() + ",");
		});




		// 只取sex为0
		System.out.println("只取sex为0****************");
		List<Demo> demorm = demos.stream().filter(demo -> demo.getSex() == 0).distinct().collect(Collectors.toList());
		demorm.forEach(demo -> {
			System.out.println("年龄 " + demo.getAge() + "  性别 " + demo.getSex() + ",");
		});
		
		// 筛选年龄大于12岁
		System.out.println("筛选年龄大于12岁的*************");
		List<Demo> demoFilter = demos.stream().filter(demo -> Integer.valueOf(demo.getAge()) > 12).collect(Collectors.toList());
		demoFilter.forEach(demo -> {
			System.out.println("年龄 " + demo.getAge() + "  性别 " + demo.getSex() + ",");
		});
		
		// 排序
		System.out.println("排序******************");
		List<Demo> demoSort = demos.stream().sorted((s1, s2) -> s1.getAge().compareTo(s2.getAge())).collect(Collectors.toList());
		demoSort.forEach(demo -> {
			System.out.println("年龄 " + demo.getAge() + "  性别 " + demo.getSex() + ",");
		});
		
		// 倒序
		System.out.println("倒序****************");
		ArrayList<Demo> demoArray = (ArrayList<Demo>) demos;
		
		Comparator<Demo> comparator = (h1, h2) -> h1.getAge().compareTo(h2.getAge());
		demos.sort(comparator.reversed());
		//or
		demos.sort(Comparator.comparing(Demo::getAge).reversed());
		
		demos.forEach(demo -> {
			System.out.println("年龄 " + demo.getAge() + "  性别 " + demo.getSex() + ",");
		});
		
		// 多条件正序
		System.out.println("多条件排序正序****************");
		demoArray.sort(Comparator.comparing(Demo::getSex).thenComparing(Demo::getAge));
		demoArray.forEach(demo -> {
			System.out.println("年龄 " + demo.getAge() + "  性别 " + demo.getSex() + ",");
		});
		
		// 多条件倒序
		System.out.println("多条件排序倒序 sex  倒序****************");
		demoArray.sort(Comparator.comparing(Demo::getSex).reversed().thenComparing(Demo::getAge));
		demoArray.forEach(demo -> {
			System.out.println("年龄 " + demo.getAge() + "  性别 " + demo.getSex() + ",");
		});
		
		// 按照年龄分组
		System.out.println("根据age分组结果为Map****************");
		Map<String, List<Demo>> demoOder = demos.stream().collect(Collectors.groupingBy(Demo::getAge));
		System.out.println(demoOder);
	}
}
 
class Demo {
	private String name;
	private Integer sex;
	private String age;
 
	public Demo(String age, Integer sex) {
		this.age = age;
		this.sex = sex;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public Integer getSex() {
		return sex;
	}
 
	public void setSex(Integer sex) {
		this.sex = sex;
	}
 
	public String getAge() {
		return age;
	}
 
	public void setAge(String age) {
		this.age = age;
	}
 
}
 
class Student {
	private String age;
	private Integer sex;
 
	public String getAge() {
		return age;
	}
 
	public void setAge(String age) {
		this.age = age;
	}
 
	public Integer getSex() {
		return sex;
	}
 
	public void setSex(Integer sex) {
		this.sex = sex;
	}
 
}
