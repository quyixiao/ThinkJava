package holding;//: holding/ListIteration.java

import typeinfo.pets.Pet;
import typeinfo.pets.Pets;

import java.util.List;
import java.util.ListIterator;


/****
 * 228页
 *
 *
 *
 * 1
 *
 *
 *
 * LinkedList也像ArrayList一样，实现了基本的List接口，但是它执行某些操作，（在List中
 * 插入和移除）时比ArrayList更高效，但随机访问的操作方面却要逊色一些
 *
 *
 * */
public class ListIteration {
    public static void main(String[] args) {
        List<Pet> pets = Pets.arrayList(8);
        ListIterator<Pet> it = pets.listIterator();
        while (it.hasNext())
            System.out.print(it.next() + ", " + it.nextIndex() +
                    ", " + it.previousIndex() + "; ");
        System.out.println();
        // Backwards:
        while (it.hasPrevious()) {
            System.out.print(it.previous().id() + " ");
        }
        System.out.println();
        System.out.println(pets);
        it = pets.listIterator(3);
        while (it.hasNext()) {
            it.next();
            it.set(Pets.randomPet());
        }
        System.out.println(pets);
    }
} /* Output:
Rat, 1, 0; Manx, 2, 1; Cymric, 3, 2; Mutt, 4, 3; Pug, 5, 4; Cymric, 6, 5; Pug, 7, 6; Manx, 8, 7;
7 6 5 4 3 2 1 0
[Rat, Manx, Cymric, Mutt, Pug, Cymric, Pug, Manx]
[Rat, Manx, Cymric, Cymric, Rat, EgyptianMau, Hamster, EgyptianMau]
*///:~
