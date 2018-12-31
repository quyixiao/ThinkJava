package holding;//: holding/InterfaceVsIterator.java

import typeinfo.pets.Pet;
import typeinfo.pets.Pets;

import java.util.*;


/***
 * 239页
 *
 *      使用接口描述的一个理由是它可以使用我们能够创建更通用的代码，通过针对接口而非具体
 *  实现来为绑定代码，我们的代码可以应用于更多的对象类型，因此，如果我们编写代码方法将接受
 *  一个Collection，那么该方法可以应用于任何实现了Collection的类，这也就是使用使得一个新类
 *  可以选择去实现Collection接口，以便我们方法可以使用它，但是，有一点是有趣的，就是我们注意
 *
 *
 *   1
 *
 */
public class InterfaceVsIterator {
    public static void display(Iterator<Pet> it) {
        while (it.hasNext()) {
            Pet p = it.next();
            System.out.print(p.id() + ":" + p + " ");
        }
        System.out.println();
    }

    public static void display(Collection<Pet> pets) {
        for (Pet p : pets)
            System.out.print(p.id() + ":" + p + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        List<Pet> petList = Pets.arrayList(8);
        Set<Pet> petSet = new HashSet<Pet>(petList);
        Map<String, Pet> petMap = new LinkedHashMap<String, Pet>();


        String[] names = ("Ralph, Eric, Robin, Lacey, " + "Britney, Sam, Spot, Fluffy").split(", ");
        for (int i = 0; i < names.length; i++) {
            petMap.put(names[i], petList.get(i));
        }


        display(petList);
        display(petSet);
        System.out.println("=======================");
        display(petList.iterator());
        display(petSet.iterator());
        System.out.println("=======================");


        System.out.println(petMap);
        System.out.println(petMap.keySet());

        System.out.println("++++++++++++++++++++");
        display(petMap.values());
        display(petMap.values().iterator());
    }
}



/* Output:
0:Rat 1:Manx 2:Cymric 3:Mutt 4:Pug 5:Cymric 6:Pug 7:Manx
4:Pug 6:Pug 3:Mutt 1:Manx 5:Cymric 7:Manx 2:Cymric 0:Rat
0:Rat 1:Manx 2:Cymric 3:Mutt 4:Pug 5:Cymric 6:Pug 7:Manx
4:Pug 6:Pug 3:Mutt 1:Manx 5:Cymric 7:Manx 2:Cymric 0:Rat
{Ralph=Rat, Eric=Manx, Robin=Cymric, Lacey=Mutt, Britney=Pug, Sam=Cymric, Spot=Pug, Fluffy=Manx}
[Ralph, Eric, Robin, Lacey, Britney, Sam, Spot, Fluffy]
0:Rat 1:Manx 2:Cymric 3:Mutt 4:Pug 5:Cymric 6:Pug 7:Manx
0:Rat 1:Manx 2:Cymric 3:Mutt 4:Pug 5:Cymric 6:Pug 7:Manx
*///:~
