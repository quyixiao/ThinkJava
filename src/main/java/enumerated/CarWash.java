package enumerated;//: enumerated/CarWash.java

import java.util.EnumSet;

import static net.mindview.util.Print.print;


/**
 *
 *
 * 605 页
 *
 *
 * 与使用匿名内部类相比较，定义常量相关的方法的语句更加的简洁，高效，这个例子中展示了EnumSet了一些特性，因为这是
 * 一些集合，所以对于同一个元素而言，只能出现一次，因此对同一个参数重复的调用add()方法会被忽略掉（这是正确的行为）
 * 除了实现abstract方法以外，程序员是否可能覆盖常量相关的方法呢，答案是肯定的
 *
 *
 * 虽然，enum有一些限制，但是一般而言，我们可能将其看作是类
 *
 *
 *
 */
public class CarWash {
    public enum Cycle {
        UNDERBODY {
            void action() {
                print("Spraying the underbody");
            }
        },
        WHEELWASH {
            void action() {
                print("Washing the wheels");
            }
        },
        PREWASH {
            void action() {
                print("Loosening the dirt");
            }
        },
        BASIC {
            void action() {
                print("The basic wash");
            }
        },
        HOTWAX {
            void action() {
                print("Applying hot wax");
            }
        },
        RINSE {
            void action() {
                print("Rinsing");
            }
        },
        BLOWDRY {
            void action() {
                print("Blowing dry");
            }
        };

        abstract void action();
    }

    EnumSet<Cycle> cycles = EnumSet.of(Cycle.BASIC, Cycle.RINSE);

    public void add(Cycle cycle) {
        cycles.add(cycle);
    }

    public void washCar() {
        for (Cycle c : cycles)
            c.action();
    }

    public String toString() {
        return cycles.toString();
    }

    public static void main(String[] args) {
        CarWash wash = new CarWash();
        print(wash);
        wash.washCar();
        // Order of addition is unimportant:
        wash.add(Cycle.BLOWDRY);
        wash.add(Cycle.BLOWDRY); // Duplicates ignored
        wash.add(Cycle.RINSE);
        wash.add(Cycle.HOTWAX);
        print(wash);
        wash.washCar();
    }
}



/* Output:
[BASIC, RINSE]
The basic wash
Rinsing
[BASIC, HOTWAX, RINSE, BLOWDRY]
The basic wash
Applying hot wax
Rinsing
Blowing dry
*///:~
