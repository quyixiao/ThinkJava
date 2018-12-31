package innerclasses;//: innerclasses/BigEgg.java
// An inner class cannot be overriden like a method.

import static net.mindview.util.Print.print;


/***
 * 213页
 *
 *
 *      内部类可以被覆盖吗？这看起来似乎是个很有用的思想，但是，覆盖内部类就好像它是
 * 外围类的一个方法，其实并不起什么作用
 *
 *
 */
class Egg {
    private Yolk y;

    protected class Yolk {
        public Yolk() {
            print("Egg.Yolk()");
        }
    }

    public Egg() {
        print("New Egg()");
        y = new Yolk();
    }
}

public class BigEgg extends Egg {
    public class Yolk {
        public Yolk() {
            print("BigEgg.Yolk()");
        }
    }

    public static void main(String[] args) {
        new BigEgg();
    }
} /* Output:
New Egg()
Egg.Yolk()
*///:~
