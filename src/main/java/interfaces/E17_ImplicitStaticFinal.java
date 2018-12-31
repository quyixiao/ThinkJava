package interfaces;


/*:interfaces/E17_ImplicitStaticFinal.java
        /****************** Exercise 17 ******************
         * Prove that the fields in an interface are
         * implicitly static and final.
         ***********************************************/

/*****
 *
 *
 * 184
 * 证明在接口中的域隐式的是static和final的
 *
 *
 * 1
 */
interface StaticFinalTest {
    String RED = "Red";
}

class Test implements StaticFinalTest {
    public Test() {
        // Compile-time error: cannot assign a value
        // to final variable RED:
        //!  RED = "Blue";
    }
}

public class E17_ImplicitStaticFinal {
    public static void main(String args[]) {
        // Accessing as a static field:
        System.out.println("StaticFinalTest.RED = " + StaticFinalTest.RED);
    }
}