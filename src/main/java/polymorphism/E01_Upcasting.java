//: polymorphism/E01_Upcasting.java
/****************** Exercise 1 ****************
 * Create a Cycle class, with subclasses
 * Unicycle, Bicycle, and Tricycle. Demonstrate
 * that an instance of each type can be upcast
 * to Cycle via a ride() method.
 ***********************************************/
package polymorphism;

import polymorphism.cycle.Bicycle;
import polymorphism.cycle.Cycle;
import polymorphism.cycle.Tricycle;
import polymorphism.cycle.Unicycle;


/****
 *
 * 150页
 * 创建一个Cycle类，它具有子类Unicycle,Biycle和Tricycle，演示第一个类型
 * 实例都可以由ride()方法身上转型的类Cycle
 *
 * 1
 */
public class E01_Upcasting {
    public static void ride(Cycle c) {
        System.out.println(c.getClass().getSimpleName());
    }


    public static void main(String[] args) {
        ride(new Cycle());    // No upcasting
        ride(new Unicycle()); // Upcast
        ride(new Bicycle());  // Upcast
        ride(new Tricycle()); // Upcast
    }
} 