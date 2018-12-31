package generics;

import typeinfo.pets.Mouse;
import typeinfo.pets.Pet;


/******
 *
 *
 *
 * 354页
 *
 * 配合typeinfo.pets类库，用Holder3来证明，如果指定Holder3可以持有某个
 *
 *
 *
 * 1
 */
public class E01_PetsHolder {
    public static void main(String[] args) {
        Holder3<Pet> h3 = new Holder3<Pet>(new Mouse("Mickey"));
        System.out.println(h3.get());
    }
}