//: enumerated/Input.java
package enumerated;

import java.util.Random;


/*****
 *  自动售货机是一个很好的状态机的例子，首先我们用一个enum定义一个enum各种输入，
 *  注意，隆子两个特殊的INput实例之外，其他的INput都有相应的价格，因此在接中中定义的
 *  ammount()方法，然而，对那两个特殊input安装实例而言，调用amount()方法并不合适，所以，如果
 *  程序员调用它们的amount()方法就会有异常抛出，在接口内定义了一个方法，然后在你调用该
 *  方法的某个实现时就会抛出异常，这似乎有点奇怪，但由于enum的限制，我们不得不彩这种方式，
 *  VendingMachine对输入的第一反应是将其归类为Category enum中11只能有一个
 *  VendingMachine实例，不过如果我们思考一下实际*/
public enum Input {
    NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100),
    TOOTHPASTE(200), CHIPS(75), SODA(100), SOAP(50),
    ABORT_TRANSACTION {
        public int amount() { // Disallow
            throw new RuntimeException("ABORT.amount()");
        }
    },
    STOP { // This must be the last instance.

        public int amount() { // Disallow
            throw new RuntimeException("SHUT_DOWN.amount()");
        }
    };
    int value; // In cents

    Input(int value) {
        this.value = value;
    }

    Input() {
    }

    int amount() {
        return value;
    }

    ; // In cents
    static Random rand = new Random(47);

    public static Input randomSelection() {
        // Don't include STOP:
        return values()[rand.nextInt(values().length - 1)];
    }
} ///:~
