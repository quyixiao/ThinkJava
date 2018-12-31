package typeinfo;

import typeinfo.pets.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unchecked")


/*****
 *
 *
 *
 * 331页
 *
 * 在typeinfo.pets类库中添加的Gerbil，并修改本章的所有的示例，让它们适应这个新类
 *
 */

public class LiteralPetCreator1 extends PetCreator {
    // No try block needed.
    public static final List<Class<? extends Pet>> allTypes =
            Collections.unmodifiableList(Arrays.asList(
                    Pet.class, Dog.class, Cat.class, Rodent.class,
                    Mutt.class, Pug.class, EgyptianMau.class, Manx.class,
                    Cymric.class, Rat.class, Mouse.class, Hamster.class,
                    Gerbil.class));
    // Types for random creation:
    private static final List<Class<? extends Pet>> types =
            allTypes.subList(allTypes.indexOf(Mutt.class),
                    allTypes.size());

    public List<Class<? extends Pet>> types() {
        return types;
    }

    public static void main(String[] args) {
        System.out.println(types);
    }
} 