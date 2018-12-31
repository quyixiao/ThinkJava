//: typeinfo/E13_PartCount.java
/****************** Exercise 13 *****************
 * Use TypeCounter with the RegisteredFactories.java
 * example in this chapter.
 ***********************************************/
package typeinfo;

import net.mindview.util.TypeCounter;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;


/******
 *
 *
 *
 * 331页
 *  将本章中的RegisteredFactories.java示例用于TypeCounter
 *
 */
public class E13_PartCount {
    public static void main(String[] args) {
        TypeCounter counter = new TypeCounter(Part.class);
        Part part;
        for (int i = 0; i < 20; i++) {
            part = Part.createRandom();
            printnb(part.getClass().getSimpleName() + " ");
            counter.count(part);
        }
        print();
        print(counter);
    }
}