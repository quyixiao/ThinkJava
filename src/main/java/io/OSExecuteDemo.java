package io;//: io/OSExecuteDemo.java
// Demonstrates standard I/O redirection.

import net.mindview.util.OSExecute;


/**
 * 551 页
 */
public class OSExecuteDemo {
    public static void main(String[] args) {
        OSExecute.command("javap /Users/quyixiao/Desktop/source/ThinkJava/src/main/java/io/OSExecuteDemo");
    }
}


/* Output:
Compiled from "OSExecuteDemo.java"
public class OSExecuteDemo extends java.lang.Object{
    public OSExecuteDemo();
    public static void main(java.lang.String[]);
}
*///:~
