package concurrency.jvm;

import sun.security.util.Resources_it;

public class MyTest9 {


    static {
        System.out.println("MyTest 9 static block");
    }

    public static void main(String[] args) {
        System.out.println(Child.b);
    }

}



class Parent{
    static int a = 3 ;


    static {
        System.out.println("parent static block");
    }

}

class Child extends Parent{
    static int b = 4;

    static {
        System.out.println("Child static block");
    }
}


