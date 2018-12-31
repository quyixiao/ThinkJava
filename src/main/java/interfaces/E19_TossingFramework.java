//: interfaces/E19_TossingFramework.java
/****************** Exercise 19 ******************
 * Create a framework using Factory Methods that
 * performs both coin tossing and dice tossing.
 ***********************************************/
package interfaces;


/*****
 * 19页
 * 使用工厂方法来创建一个框架，它可以执行抛硬币和掷骰子功能
 *
 *
 * 1
 */
interface Tossing {
    boolean event();
}


class CoinTossing implements Tossing {
    private int events;
    private static final int EVENTS = 2;

    public boolean event() {
        System.out.println("Coin tossing event " + events);
        return ++events != EVENTS;
    }
}

class DiceTossing implements Tossing {
    private int events;
    private static final int EVENTS = 6;

    public boolean event() {
        System.out.println("Dice tossing event " + events);
        return ++events != EVENTS;
    }
}



interface TossingFactory {
    Tossing getTossing();
}

class CoinTossingFactory implements TossingFactory {
    public CoinTossing getTossing() {
        return new CoinTossing();
    }
}

class DiceTossingFactory implements TossingFactory {
    public DiceTossing getTossing() {
        return new DiceTossing();
    }
}

public class E19_TossingFramework {
    public static void simulate(TossingFactory fact) {
        Tossing t = fact.getTossing();
        while (t.event())
            ;
    }

    public static void main(String[] args) {
        simulate(new CoinTossingFactory());
        simulate(new DiceTossingFactory());
    }
}