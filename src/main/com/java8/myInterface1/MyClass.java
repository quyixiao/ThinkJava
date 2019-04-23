package com.java8.myInterface1;


/***
 * List
 *  sort 方法 默认方法
 *
 * List
 *  MyList
 *
 */
public class MyClass implements MyInterface1, MyInterface2 {


    @Override
    public void myMethod() {
        MyInterface2.super.myMethod2();
    }

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        //myClass.myMethod();
        // myClass.myMethod2();
        myClass.myMethod();





    }


}
