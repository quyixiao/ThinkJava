//: generics/E14_BasicGeneratorDemo2.java
/****************** Exercise 14 *****************
 * Modify BasicGeneratorDemo.java to use the
 * explicit form of creation for the Generator
 Generics
 341
 * (that is, use the explicit constructor instead
 * of the generic create() method).
 ************************************************/
package generics;

import net.mindview.util.BasicGenerator;
import net.mindview.util.Generator;

/*****
 *
 * 365页
 * 修改BasicGeneratorDemo.java 类，使其显式地构造Generator(也就是不使用create()方法，
 * 而是使用显式的构造器)
 *
 *
 * 1
 */
public class E14_BasicGeneratorDemo2 {
    public static void main(String[] args) {
        Generator<CountedObject> gen = new BasicGenerator<CountedObject>(CountedObject.class);
        for (int i = 0; i < 5; i++)
            System.out.println(gen.next());
    }
}