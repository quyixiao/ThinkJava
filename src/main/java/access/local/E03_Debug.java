//: access/debug/E03_Debug.java
/****************** Exercise 3 ****************
 * Create two packages: debug and debugoff,
 * containing an identical class with a debug()
 * method. The first version displays its String
 * argument to the console, the second does nothing.
 * Import the class into a test program
 * using a static import line, and demonstrate
 * the conditional compilation effect.
 ***********************************************/
package access.local;

public class E03_Debug {
    public static void debug(String msg) {
        System.out.println("Message: " + msg);
    }
} ///:~
//: access/debugoff/E03_Debug.java
        ///:~
/*
//: access/E03_DebugApp.java
         package access;
                 import static access.debug.E03_Debug.*;

public class E03_DebugApp {
    public static void main(String[] args) {
        debug("DEBUG VERSION");
    }
} */
/* Output:
         Message: DEBUG VERSION
         *//*
//:~
//: access/E03_ReleaseApp.java
         package access;
                 import static access.debugoff.E03_Debug.*;

public class E03_ReleaseApp {
    public static void main(String[] args) {


        debug("RELEASE VERSION");
    }
}*/
