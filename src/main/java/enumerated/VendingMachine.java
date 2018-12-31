//: enumerated/VendingMachine.java
// {Args: VendingMachineInput.txt}
package enumerated;

import net.mindview.util.Generator;
import net.mindview.util.TextFile;

import java.util.EnumMap;
import java.util.Iterator;

import static enumerated.Input.*;
import static net.mindview.util.Print.print;


/****
 * 612页
 *
 *
 * 由于用switch语句从enum实例中进行选择是最常见的一种方式，请注意，为了使enum在swithch 语句中的使用变
 * 得简单，我们是需要付出其它的代价的，所以，我们经常遇到这样的问题，将多个enum进行分类时，我们希望在什么enum中使用
 * swithch语句，我们通过VendingMachine 的例子来研究一下这个问题，对于每一个State，我们都要在输入动作的基本分类
 * 中进行查找，用户塞入不同类型的钞票，可以选择不现的事物，Category enum将不同的类型的Input进行分组，因而，可以
 * 使用categorize()方法为swithch 语句生成恰当的Cateroy实例，并且，该方法使用的EnumMap 确保了在其他的查询的
 * 效率，与安全
 * 如果 读者仔细的研究VendingMachine类。就会发现每种状态的不同之处，以及对于输入的不同响应，其中还有两个瞬间
 * 状态，在run()方法中，状态机等侍的着下一个Input，并一直在和个状态中移动，直到它不再处于瞬间状态。
 *
 * 通过两种不同的Generator对象，我们可以用两种方式来测试VendingMachene,首先是RandomINputGenerator，它会不停的
 * 生成各种健全测试的作用，能够确保该状态机不会进入一个错误的状态，另一个是FileinputGenetor，使用文件以文件的方式
 * 来描述输入，然后将它们转换成enum实例，并创建对应的Input对象，上面的程序使用的正是如下的文本文件


 
 *
 *
 *
 * 我们可以用两种方式来测试VendingMachine，
 *
 *
 */
enum Category {
    MONEY(NICKEL, DIME, QUARTER, DOLLAR),
    ITEM_SELECTION(TOOTHPASTE, CHIPS, SODA, SOAP),
    QUIT_TRANSACTION(ABORT_TRANSACTION),
    SHUT_DOWN(STOP);
    private Input[] values;

    Category(Input... types) {
        values = types;
    }

    private static EnumMap<Input, Category> categories =
            new EnumMap<Input, Category>(Input.class);

    static {
        for (Category c : Category.class.getEnumConstants())
            for (Input type : c.values)
                categories.put(type, c);
    }

    public static Category categorize(Input input) {
        return categories.get(input);
    }
}

public class VendingMachine {
    private static State state = State.RESTING;
    private static int amount = 0;
    private static Input selection = null;

    enum StateDuration {TRANSIENT} // Tagging enum

    enum State {
        RESTING {
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        state = ADDING_MONEY;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        ADDING_MONEY {
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        break;
                    case ITEM_SELECTION:
                        selection = input;
                        if (amount < selection.amount())
                            print("Insufficient money for " + selection);
                        else state = DISPENSING;
                        break;
                    case QUIT_TRANSACTION:
                        state = GIVING_CHANGE;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        DISPENSING(StateDuration.TRANSIENT) {
            void next() {
                print("here is your " + selection);
                amount -= selection.amount();
                state = GIVING_CHANGE;
            }
        },
        GIVING_CHANGE(StateDuration.TRANSIENT) {
            void next() {
                if (amount > 0) {
                    print("Your change: " + amount);
                    amount = 0;
                }
                state = RESTING;
            }
        },
        TERMINAL {
            void output() {
                print("Halted");
            }
        };
        private boolean isTransient = false;

        State() {
        }

        State(StateDuration trans) {
            isTransient = true;
        }

        void next(Input input) {
            throw new RuntimeException("Only call " +
                    "next(Input input) for non-transient states");
        }

        void next() {
            throw new RuntimeException("Only call next() for " +
                    "StateDuration.TRANSIENT states");
        }

        void output() {
            print(amount);
        }
    }

    static void run(Generator<Input> gen) {
        while (state != State.TERMINAL) {
            state.next(gen.next());
            while (state.isTransient)
                state.next();
            state.output();
        }
    }

    public static void main(String[] args) {
        Generator<Input> gen = new RandomInputGenerator();
        if (args.length == 1)
            gen = new FileInputGenerator(args[0]);
        run(gen);
    }
}

// For a basic sanity check:
class RandomInputGenerator implements Generator<Input> {
    public Input next() {
        return Input.randomSelection();
    }
}

// Create Inputs from a file of ';'-separated strings:
class FileInputGenerator implements Generator<Input> {
    private Iterator<String> input;

    public FileInputGenerator(String fileName) {
        input = new TextFile(fileName, ";").iterator();
    }

    public Input next() {
        if (!input.hasNext())
            return null;
        return Enum.valueOf(Input.class, input.next().trim());
    }
} /* Output:
25
50
75
here is your CHIPS
0
100
200
here is your TOOTHPASTE
0
25
35
Your change: 35
0
25
35
Insufficient money for SODA
35
60
70
75
Insufficient money for SODA
75
Your change: 75
0
Halted
*///:~
