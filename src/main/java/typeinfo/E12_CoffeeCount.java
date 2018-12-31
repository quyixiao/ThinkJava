//: typeinfo/E12_CoffeeCount.java
/****************** Exercise 12 *****************
 * Use TypeCounter with the CoffeeGenerator.java
 * class in the Generics chapter.
 ***********************************************/
package typeinfo;

import generics.coffee.Coffee;
import generics.coffee.CoffeeGenerator;
import net.mindview.util.TypeCounter;

import java.util.Iterator;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;


/******
 *
 *
 *
 * 311页
 *
 * 将第15章中的CoffeeGenerator.java类用于TypeCounter
 *
 *
 */
public class E12_CoffeeCount {
    public static void main(String[] args) {
        TypeCounter counter = new TypeCounter(Coffee.class);
        for (Iterator<Coffee> it =
             new CoffeeGenerator(20).iterator(); it.hasNext(); ) {
            Coffee coffee = it.next();
            printnb(coffee.getClass().getSimpleName() + " ");
            counter.count(coffee);
        }
        print();
        print(counter);
    }
}