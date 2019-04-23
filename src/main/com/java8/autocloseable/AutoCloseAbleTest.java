package com.java8.autocloseable;

/***
 * close()方法一定会调用，jdk7一定会调用，在这种情况下，一定会调用的
 *
 *
 * public interface Stream<T> extends BaseStream<T,Stream<T>>
 *
 *
 *
 *
 *
 *
 *
 */
public class AutoCloseAbleTest implements AutoCloseable {


    public void doSomeThing() {
        System.out.println("doSomething invoked!");
    }


    @Override
    public void close() throws Exception {
        System.out.println("close invoked!");
    }


    public static void main(String[] args) throws Exception {
        try (AutoCloseAbleTest autoCloseAbleTest = new AutoCloseAbleTest()) {
            autoCloseAbleTest.doSomeThing();
        }
    }
}
