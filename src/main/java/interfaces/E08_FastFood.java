//: interfaces/E08_FastFood.java
/****************** Exercise 8 ******************
 * Create an interface called FastFood (with
 * appropriate methods) in
 * polymorphism.Sandwich.java, and change Sandwich
 * so it also implements FastFood.
 ***********************************************/
package interfaces;

import polymorphism.Sandwich;



import static net.mindview.util.Print.print;


/******
 * 174页
 * 在polymorphism.sandwith.java中，创建接口FastFood并添加合适的方法，然后
 * 修改Sandwich以实现FastFood接口
 *
 *
 * 1
 *
 */
interface FastFood {
    void rushOrder();

    void gobble();
}

class FastSandwich extends Sandwich implements FastFood {
    public void rushOrder() {
        print("Rushing your sandwich order");
    }

    public void gobble() {
        print("Chomp! Snort! Gobble!");
    }
}

public class E08_FastFood {
    public static void main(String args[]) {
        FastSandwich burger = new FastSandwich();
        print("Fries with that?");
        print("Super Size?");
        burger.rushOrder();
        burger.gobble();
    }
}