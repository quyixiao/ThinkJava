package containers;//: containers/IndividualTest.java

import holding.MapOfList;
import typeinfo.pets.Individual;
import typeinfo.pets.Pet;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * 498页
 * 不懂
 *
 */
public class IndividualTest {
    public static void main(String[] args) {
        Set<Individual> pets = new TreeSet<Individual>();
        for (List<? extends Pet> lp :
                MapOfList.petPeople.values())
            for (Pet p : lp)
                pets.add(p);
        System.out.println(pets);
    }
} /* Output:
[Cat Elsie May, Cat Pinkola, Cat Shackleton, Cat Stanford aka Stinky el Negro, Cymric Molly, Dog Margrett, Mutt Spot, Pug Louie aka Louis Snorkelstein Dupree, Rat Fizzy, Rat Freckly, Rat Fuzzy]
*///:~
