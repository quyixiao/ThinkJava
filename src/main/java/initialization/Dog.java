package initialization;


//: initialization/E05_OverloadedDog.java

/****************** Exercise 5 *****************
 * Create a class called Dog with an overloaded
 * bark() method. Your method should be
 * overloaded based on various primitive data
 * types, and should print different types of barking,
 * howling, etc., depending on which overloaded
 * version is called. Write a main() that calls
 Initialization & Cleanup 51
 * all the different versions.
 ***********************************************/


/****
 * 83页
 * 创建一个名为Dog的类，它具有重载的Bark()方法，此方法应根据不同的基本数据类型
 * 进行重载，并根据被调用的版本，打印出不同的类型的狂吠（barking），咆哮（howling）等信息
 * ，编写main()来调用所用的所有不同的版本的方法
 *
 *
 */


class Dog {
    public void bark() {
        System.out.println("Default bark!");
    }

    public void bark(int i) {
        System.out.println("int bark = howl");
    }

    public void bark(double d) {
        System.out.println("double bark = yip");
    }
// Etc. ...
//
}