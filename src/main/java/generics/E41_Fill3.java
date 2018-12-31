//: generics/E41_Fill3.java
/****************** Exercise 41 *****************
 * Modify Fill2.java to use the classes in
 * typeinfo.pets instead of the Coffee classes.
 ************************************************/
package generics;

import typeinfo.pets.Cymric;
import typeinfo.pets.Mouse;
import typeinfo.pets.Mutt;
import typeinfo.pets.Pet;

import java.util.ArrayList;
import java.util.List;

import static net.mindview.util.Print.print;

/****
 *
 *
 * 426页
 *  修改Fill2.java,用typeinfo.java中的类取代Coffee中的类
 *
 *
 * 我们创建执行适配操作的函数对象，而它们将被传递到用作策略的方法中
 *
 *
 *
 * 1
 *
 *
 *
 *
 *
 */
public class E41_Fill3 {
    public static void main(String[] args) throws Exception {
        // Adapt a Collection:
        List<Pet> carrier = new ArrayList<Pet>();
        Fill2.fill(
                new AddableCollectionAdapter<Pet>(carrier),
                Pet.class, 3);
        // Helper method captures the type:
        Fill2.fill(Adapter.collectionAdapter(carrier),
                Mouse.class, 2);
        for (Pet p : carrier)
            print(p);
        print("----------------------");
        // Use an adapted class:
        AddableSimpleQueue<Pet> petQueue =
                new AddableSimpleQueue<Pet>();
        Fill2.fill(petQueue, Mutt.class, 4);
        Fill2.fill(petQueue, Cymric.class, 1);
        for (Pet p : petQueue)
            print(p);
    }
}