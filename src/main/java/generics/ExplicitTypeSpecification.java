package generics;//: generics/ExplicitTypeSpecification.java

import net.util.New;
import typeinfo.pets.Person;
import typeinfo.pets.Pet;

import java.util.List;
import java.util.Map;


/****
 *
 *
 * 363页
 *
 *
 * 1
 *
 */
public class ExplicitTypeSpecification {
  static void f(Map<Person, List<Pet>> petPeople) {}



  public static void main(String[] args) {
    f(New.<Person, List<Pet>>map());
  }
} ///:~
