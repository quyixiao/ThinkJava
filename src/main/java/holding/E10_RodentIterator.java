//: holding/E10_RodentIterator.java
/****************** Exercise 10 ******************
 * change Exercise 9 in the Polymorphism chapter
 * to use an ArrayList to hold the Rodents and an
 * Iterator to move through their sequence.
 ***********************************************/
package holding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static net.mindview.util.Print.print;

/******
 * 227
 *      修改第8章中的练习9，使其使用一个ArrayList来存放Rodents，并使用一个
 * Iterator来访问Rodent序列
 *
 *
 * 1
 */
class Rodent {
    public void hop() {
        print("Rodent hopping");
    }

    public void scurry() {
        print("Rodent scurrying");
    }

    public void reproduce() {
        print("Making more Rodents");
    }

    public String toString() {
        return "Rodent";
    }
}

class Mouse extends Rodent {
    public void hop() {
        print("Mouse hopping");
    }

    public void scurry() {
        print("Mouse scurrying");
    }

    public void reproduce() {
        print("Making more Mice");
    }

    public String toString() {
        return "Mouse";
    }
}

class Hamster extends Rodent {
    public void hop() {
        print("Hamster hopping");
    }

    public void scurry() {
        print("Hamster scurrying");
    }

    public void reproduce() {
        print("Making more Hamsters");
    }

    public String toString() {
        return "Hamster";
    }
}

public class E10_RodentIterator {
    public static void main(String args[]) {
        ArrayList<Rodent> rodents = new ArrayList<Rodent>(
                Arrays.asList(
                        new Rodent(), new Mouse(), new Hamster()));
        Rodent r;
        for (Iterator<Rodent> it = rodents.iterator();
             it.hasNext(); ) {
            r = it.next();
            r.hop();
            r.scurry();
            r.reproduce();
            print(r);
        }
    }
}