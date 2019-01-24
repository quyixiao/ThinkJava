package enumerated;//: enumerated/TrafficLight.java
// Enums in switch statements.

import static net.mindview.util.Print.print;


/**
 *
 *
 * 594 页
 *
 *
 *  在switch 中使enum ,是enum 提供的一项非常便利的功能，一般来说，在switch 中只能
 *  使用数值，而枚举实例天生的就具备整数的次序，并且可以通过originl() 方法取得其次序，
 *  显然，编译器做了类似的工作，因此我们可以在switch语句中使用enum
 *   虽然一般情况下我们必须用enum类型来修饰一个enum 实例，但是我们，但是在case 语句中却不必如
 *   此，下面的例子中使用enum 构造了下个小型的状态机
 *

 *    编译器并没有抱怨switch中没有default语句，语句，但这并不是因为每一个Signal都有对就
 *    case 语句，如果你注释掉其中某个case 语句，编译器同样不会抱怨什么，这意味着，你必确定
 *    ，你自己覆盖掉所有的分支，但是，如果在case 语句中调用return ,那么编译器就会抱怨缺少default 语句了，这与是否覆盖
 *    了enum的覆盖实例无关
 *
 *
 *
 *
 * 1
 *
 *
 *
 *
 *
 */
// Define an enum type:
enum Signal {
    GREEN, YELLOW, RED,
}

public class TrafficLight {
    Signal color = Signal.RED;

    public void change() {
        switch (color) {
            // Note that you don't have to say Signal.RED
            // in the case statement:
            case RED:
                color = Signal.GREEN;
                break;
            case GREEN:
                color = Signal.YELLOW;
                break;
            case YELLOW:
                color = Signal.RED;
                break;
        }
    }

    public String toString() {
        return "The traffic light is " + color;
    }

    public static void main(String[] args) {
        TrafficLight t = new TrafficLight();
        for (int i = 0; i < 7; i++) {
            print(t);
            t.change();
        }
    }
}



/* Output:
The traffic light is RED
The traffic light is GREEN
The traffic light is YELLOW
The traffic light is RED
The traffic light is GREEN
The traffic light is YELLOW
The traffic light is RED
*///:~
