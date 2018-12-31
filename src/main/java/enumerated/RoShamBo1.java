//: enumerated/RoShamBo1.java
// Demonstration of multiple dispatching.
package enumerated;

import java.util.Random;

import static enumerated.Outcome.*;


/**
 * 613 页
 * <p>
 * <p>
 * 当你要处理多个交互类型时，程序可能会变得相当的杂乱，举例来说，如果一个系统要分析和执行数学
 * 表达式，我们可能会声明Number.plus(Numer)，Number.multiple(Number)等等，其中number是各种
 * 数字对象的超类，然而，当你声明a.plus(b)时，你并不知道a或b的确切的类型，那你如何能让它们正确的
 * 交互呢。
 * <p>
 * 你可能从未思考过问题的答案，Java只支持单路分发，也就是说，如果要执行的操作包含了不止一个类型未知
 * 对象时，那么Java的动态绑定机制只能处理其中的一个类型，这就无法解决我们上面的问题，所以你必须自己来
 * 判定其他的类型，从而实现自己的动态绑定行为。
 * <p>
 * <p>
 * 解决上面问题办法就是多路分发，在那个例子中，只有两个分发，一般称之为两路分发，多态只能发生在方法调用时
 * ，所以，如果你想使用处理不同的类型体系，就需要为每个类型体系执行一个方法调用，一般而言，程序员需要有设定好的
 * 某种配置，以便一个方法调用能够引出更多的方法调用，从而能够在这个过程中处理多种类型，为了达到这种效果，我们
 * 需要与多个方法一同工作，因为每个分发都一个方法调用，在下面的例子中，实现了石头，剪刀 布，游戏，也称为RoShamBo
 * 对应的方法是compete()和eval()二者都是同一个类型的成员，它们可能产生三种outcom实例中的一个作为结果
 * <p>
 * <p>
 * Item 是这几种类型的接口，将会
 */
interface Item {
    Outcome compete(Item it);

    Outcome eval(Paper p);

    Outcome eval(Scissors s);

    Outcome eval(Rock r);
}

class Paper implements Item {
    public Outcome compete(Item it) {
        return it.eval(this);
    }

    public Outcome eval(Paper p) {
        return DRAW;
    }

    public Outcome eval(Scissors s) {
        return WIN;
    }

    public Outcome eval(Rock r) {
        return LOSE;
    }

    public String toString() {
        return "Paper";
    }
}

class Scissors implements Item {
    public Outcome compete(Item it) {
        return it.eval(this);
    }

    public Outcome eval(Paper p) {
        return LOSE;
    }

    public Outcome eval(Scissors s) {
        return DRAW;
    }

    public Outcome eval(Rock r) {
        return WIN;
    }

    public String toString() {
        return "Scissors";
    }
}

class Rock implements Item {
    public Outcome compete(Item it) {
        return it.eval(this);
    }

    public Outcome eval(Paper p) {
        return WIN;
    }

    public Outcome eval(Scissors s) {
        return LOSE;
    }

    public Outcome eval(Rock r) {
        return DRAW;
    }

    public String toString() {
        return "Rock";
    }
}

public class RoShamBo1 {
    static final int SIZE = 20;
    private static Random rand = new Random(47);

    public static Item newItem() {
        switch (rand.nextInt(3)) {
            default:
            case 0:
                return new Scissors();
            case 1:
                return new Paper();
            case 2:
                return new Rock();
        }
    }

    public static void match(Item a, Item b) {
        System.out.println(
                a + " vs. " + b + ": " + a.compete(b));
    }

    public static void main(String[] args) {
        for (int i = 0; i < SIZE; i++)
            match(newItem(), newItem());
    }
}


/* Output:
Rock vs. Rock: DRAW
Paper vs. Rock: WIN
Paper vs. Rock: WIN
Paper vs. Rock: WIN
Scissors vs. Paper: WIN
Scissors vs. Scissors: DRAW
Scissors vs. Paper: WIN
Rock vs. Paper: LOSE
Paper vs. Paper: DRAW
Rock vs. Paper: LOSE
Paper vs. Scissors: LOSE
Paper vs. Scissors: LOSE
Rock vs. Scissors: WIN
Rock vs. Paper: LOSE
Paper vs. Rock: WIN
Scissors vs. Paper: WIN
Paper vs. Scissors: LOSE
Paper vs. Scissors: LOSE
Paper vs. Scissors: LOSE
Paper vs. Scissors: LOSE
*///:~
