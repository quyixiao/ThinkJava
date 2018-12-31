//: polymorphism/E05_Wheels.java
/****************** Exercise 5 ****************
 * Starting from Exercise 1, add a wheels()
 * method in Cycle, which returns the number of
 * wheels. Modify ride() to call wheels() and
 * verify that polymorphism works.
 ***********************************************/
package polymorphism;

import polymorphism.cycle2.*;


/*******
 *  153页
 *  以练习1为基础，在Cycle中添加wheels()方法，它将返回轮子的数量，修改ride()
 *  方法，让它调用hweels()方法，并难多态起作用了
 *
 */
public class E05_Wheels {
    public static void ride(Cycle c) {
        System.out.println("Num. of wheels: " + c.wheels());
    }

    public static void main(String[] args) {
        ride(new Unicycle());
        ride(new Bicycle());
        ride(new Tricycle());
    }
}