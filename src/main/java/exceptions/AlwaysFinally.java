package exceptions;//: exceptions/AlwaysFinally.java
// Finally is always executed.

import static net.mindview.util.Print.print;

class FourException extends Exception {
}


/***
 * 267页
 *
 * 1
 *
 *
 *
 *  如果把finally子句和带标签的break及continue配合使用，在java中没必要使用goto语句了
 *
 *
 *
 *
 *
 */
public class AlwaysFinally {
    public static void main(String[] args) {
        print("Entering first try block");
        try {
            print("Entering second try block");
            try {
                throw new FourException();
            } finally {
                print("finally in 2nd try block");
            }
        } catch (FourException e) {
            System.out.println(
                    "Caught FourException in 1st try block");
        } finally {
            System.out.println("finally in 1st try block");
        }
    }
}



/* Output:
Entering first try block
Entering second try block
finally in 2nd try block
Caught FourException in 1st try block
finally in 1st try block
*///:~
