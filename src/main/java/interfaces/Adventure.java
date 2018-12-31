package interfaces;//: interfaces/Adventure.java
// Multiple interfaces.


/***
 * 179dmu
 *
 *
 * 1
 */
interface CanFight {
    void fight();
}

interface CanSwim {
    void swim();
}

interface CanFly {
    void fly();
}

class ActionCharacter {
    public void fight() {
    }
}

class Hero extends ActionCharacter
        implements CanFight, CanSwim, CanFly {
    public void swim() {
    }

    public void fly() {
    }
}

public class Adventure {
    public static void t(CanFight x) {
        x.fight();
    }

    public static void u(CanSwim x) {
        x.swim();
    }

    public static void v(CanFly x) {
        x.fly();
    }

    public static void w(ActionCharacter x) {
        x.fight();
    }

    /**
     * 为了能够向上转型为多个基类型，以及由此带来的灵活性，然而，使用接口的第二个原因却是与使用抽象
     * 基类相同，防止客户端程序员创建该类的对象，并确保这仅仅是建立一个接口，这就带来了一个问题，我们
     * 应该使用接口还是抽象的对象，如果要创建不带任何方法定义的和成员变量的的基类，那么就应该选择接口而
     * 不是抽象类，事实上，如果知道某事物应该成为一个基类，那么第一选择应该是使它成为一个接口，
     *
     * @param args
     */
    public static void main(String[] args) {
        Hero h = new Hero();
        t(h); // Treat it as a CanFight
        u(h); // Treat it as a CanSwim
        v(h); // Treat it as a CanFly
        w(h); // Treat it as an ActionCharacter
    }
} ///:~
