package concurrency.jvm;

import sun.security.util.Resources_it;

public class MyTest7 {
    public static void main(String[] args)  throws Exception{
        Class<?> clazz = Class.forName("java.lang.String");
        System.out.println(clazz.getClassLoader());



        Class<?> class2 = Class.forName("concurrency.jvm.C");
        System.out.println(class2.getClassLoader());

    }



}


class C{

}




