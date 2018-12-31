//: generics/E37_Mixins2.java
/****************** Exercise 37 *****************
 * Add a new mixin class Colored to Mixins.java,
 * mix it in to Mixin, and show that it works.
 ************************************************/
package generics;

import java.awt.*;
import java.util.Random;


/****
 *
 *
 * 414页
 *  向mixins.java中不回一个新的混型类，将其混入到Minxin中，并展示其是可以工作的
 *
 *
 *
 * 1
 */
interface Colored11 {
    Color getColor();
}

class ColoredImp implements Colored11 {
    private static Random rnd = new Random(47);
    private final Color clr = new Color(rnd.nextInt(16777216));  // 2^24

    public Color getColor() {
        return clr;
    }
}

class Mixin2 extends Mixin implements Colored11 {
    private Colored11 colored = new ColoredImp();

    public Color getColor() {
        return colored.getColor();
    }
}

public class E37_Mixins2 {
    public static void main(String[] args) {
        Mixin2 mixin1 = new Mixin2(), mixin2 = new Mixin2();
        mixin1.set("test string 1");
        mixin2.set("test string 2");
        System.out.println(mixin1.get() + " " +
                mixin1.getStamp() + " " +
                mixin1.getSerialNumber() + " " + mixin1.getColor());
        System.out.println(mixin2.get() + " " +
                mixin2.getStamp() + " " +
                mixin2.getSerialNumber() + " " + mixin2.getColor());
    }
}