package holding;//: holding/SimpleIteration.java

import typeinfo.pets.Pet;
import typeinfo.pets.Pets;

import java.util.Iterator;
import java.util.List;


/*****
 *
 *
 * 226页
 *
 *      使用方法Iterator()要求容器返回一个Iterator，Iterator将准备好返回序列的第一个元素
 *      使用Next()获得序列中的下一人元素
 *      使用hasNext()检查序列中是否还有元素
 *      使用remove()将迭代器新近返回的元素删除
 *
 *
 *      有了Iterator就不必为容器中的元素的数量操心了，那是由hasNext()和next()关心的事情
 *      如果只是向前遍历List，并不打算修改List对象中的元素，这意味着在调用remove()之前先调用next()
 *      接受对象容器并传递他，从而在每个对象中都执行操作，这种思想十分强大，并且贯穿于本书
 *
 *
 * 1
 *
 *
 */
public class SimpleIteration {
    public static void main(String[] args) {
        List<Pet> pets = Pets.arrayList(12);
        Iterator<Pet> it = pets.iterator();
        while (it.hasNext()) {
            Pet p = it.next();
            System.out.print(p.id() + ":" + p + " ");
        }
        System.out.println();
        // A simpler approach, when possible:
        for (Pet p : pets)
            System.out.print(p.id() + ":" + p + " ");
        System.out.println();
        // An Iterator can also remove elements:
        it = pets.iterator();
        for (int i = 0; i < 6; i++) {
            it.next();
            it.remove();
        }
        System.out.println(pets);
    }
}



/* Output:
0:Rat 1:Manx 2:Cymric 3:Mutt 4:Pug 5:Cymric 6:Pug 7:Manx 8:Cymric 9:Rat 10:EgyptianMau 11:Hamster
0:Rat 1:Manx 2:Cymric 3:Mutt 4:Pug 5:Cymric 6:Pug 7:Manx 8:Cymric 9:Rat 10:EgyptianMau 11:Hamster
[Pug, Manx, Cymric, Rat, EgyptianMau, Hamster]
*///:~
