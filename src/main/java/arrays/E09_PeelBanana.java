//: arrays/E09_PeelBanana.java
/****************** Exercise 9 *****************
 * Create the classes necessary for the Peel<Banana>
 * example and show that the compiler doesn’t accept
 * it. Fix the problem using an ArrayList.
 ***********************************************/
package arrays;

import java.util.ArrayList;


/****
 *
 *
 * 442页
 *      创建Peel<Bannana>所必需的类，并展示编译器不会接受它，使用ArrayList来改正
 *  此问题
 */
class Banana {
    private final int id;

    Banana(int id) {
        this.id = id;
    }

    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }
}

class Peel<T> {
    T fruit;

    Peel(T fruit) {
        this.fruit = fruit;
    }

    void peel() {
        System.out.println("Peeling " + fruit);
    }
}

public class E09_PeelBanana {
    public static void main(String[] args) {
        // Compile error:
        //! Peel<Banana>[] a = new Peel<Banana>[10];
        ArrayList<Peel<Banana>> a =
                new ArrayList<Peel<Banana>>();
        for (int i = 0; i < 10; i++)
            a.add(new Peel<Banana>(new Banana(i)));
        for (Peel<Banana> p : a)
            p.peel();
    }
}