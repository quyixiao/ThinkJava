package holding;//: holding/PetMap.java

import typeinfo.pets.Cat;
import typeinfo.pets.Dog;
import typeinfo.pets.Hamster;
import typeinfo.pets.Pet;

import java.util.HashMap;
import java.util.Map;

import static net.mindview.util.Print.print;


/***
 *
 * 1
 *    下面的示例允许你使用一个String描述来查找 Pet,它还展示了你可以使用怎样的方法通过
 *  使用containKey()和containsValue()来测试一个Map，以便查看它的某个会键或某个值
 *
 *
 *
 *
 *  234页
 */
public class PetMap {
  public static void main(String[] args) {
    Map<String,Pet> petMap = new HashMap<String,Pet>();
    petMap.put("My Cat", new Cat("Molly"));
    petMap.put("My Dog", new Dog("Ginger"));
    petMap.put("My Hamster", new Hamster("Bosco"));
    print(petMap);
    Pet dog = petMap.get("My Dog");
    print(dog);
    print(petMap.containsKey("My Dog"));
    print(petMap.containsValue(dog));
  }
} /* Output:
{My Cat=Cat Molly, My Hamster=Hamster Bosco, My Dog=Dog Ginger}
Dog Ginger
true
true
*///:~
