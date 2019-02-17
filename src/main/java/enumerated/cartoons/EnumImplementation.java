//: enumerated/cartoons/EnumImplementation.java
// An enum can implement an interface
package enumerated.cartoons;

import net.mindview.util.Generator;

import java.util.Random;


/***
 *
 *
 * 596页
 *
 *
 * 然而，在我们创建一个新的enums时，可以同时实现一个或多个接口：
 *
 * 我们已经知道，所有的enum都继承自java.lang.Enums类，由于，java不支持多重继承，所以
 * 你的enum不能再继承其他类，
 *
 *
 *
 * 1
 *
 */
enum CartoonCharacter
        implements Generator<CartoonCharacter> {
    SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;
    private Random rand = new Random(47);

    public CartoonCharacter next() {
        return values()[rand.nextInt(values().length)];
    }
}

public class EnumImplementation {
    public static <T> void printNext(Generator<T> rg) {
        System.out.print(rg.next() + ", ");
    }

    public static void main(String[] args) {
        // Choose any instance:
        CartoonCharacter cc = CartoonCharacter.BOB;
        for (int i = 0; i < 10; i++)
            printNext(cc);
    }
} /* Output:
BOB, PUNCHY, BOB, SPANKY, NUTTY, PUNCHY, SLAPPY, NUTTY, NUTTY, SLAPPY,
*///:~
