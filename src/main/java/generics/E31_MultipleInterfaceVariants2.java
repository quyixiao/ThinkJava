//: generics/E31_MultipleInterfaceVariants2.java
/****************** Exercise 31 *****************
 * Remove all the generics from
 * MultipleInterfaceVariants.java and modify the
 * code so that the example compiles.
 ************************************************/
package generics;

interface Payable1 {
}

class Employee2 implements Payable1 {
}

class Hourly2 extends Employee2 implements Payable1 {
}


/***
 * 402页
 * 练习31
 * 从MultipleInterFaceVarnats.java中移除所有的泛型，并修改代码，使这个示例可编译
 *
 *
 *
 * 1
 *
 */
public class E31_MultipleInterfaceVariants2 {
    public static void main(String[] args) {
        new Employee();
        new Hourly2();
    }
}