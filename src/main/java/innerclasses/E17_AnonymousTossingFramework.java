//: innerclasses/E17_AnonymousTossingFramework.java
/****************** Exercise 17 ******************
 * Use anonymous inner classes to modify the solution
 * to Exercise 19 from the Interfaces chapter.
 ***********************************************/
package innerclasses;


/*****
 * 17页
 *
 * 修改第9章中的练习19的解决方案，让它使用匿名内部类
 *
 */
interface Tossing {
    boolean event();
}

interface TossingFactory {
    Tossing getTossing();
}

class CoinTossing implements Tossing {
    private int events;
    private static final int EVENTS = 2;

    public boolean event() {
        System.out.println("Coin tossing event " + events);
        return ++events != EVENTS;
    }

    public static TossingFactory factory =
            new TossingFactory() {
                public CoinTossing getTossing() {
                    return new CoinTossing();
                }
            };
}

class DiceTossing implements Tossing {
    private int events;
    private static final int EVENTS = 6;

    public boolean event() {
        System.out.println("Dice tossing event " + events);
        return ++events != EVENTS;
    }

    public static TossingFactory factory =
            new TossingFactory() {
                public DiceTossing getTossing() {
                    return new DiceTossing();
                }
            };
}

public class E17_AnonymousTossingFramework {
    public static void simulate(TossingFactory fact) {
        Tossing t = fact.getTossing();
        while (t.event())
            ;
    }

    public static void main(String[] args) {
        simulate(CoinTossing.factory);
        simulate(DiceTossing.factory);
    }
}