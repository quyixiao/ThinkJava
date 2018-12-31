//: polymorphism/Sandwich.java
// Order of constructor calls.
package polymorphism;

import static net.mindview.util.Print.print;


/****
 *
 * 122页
 *
 *
 *
 * 1
 *
 *
 *  调用基类构造器，这个步骤会不断地返单递归下去，首先是构造这层次结构的根，然后是下一层
 *  出类，等等，直到最低层的导出类。
 *  按声明顺序调用成员的初始化方法
 *  调用导出类构造器的主体
 *
 *
 */
class Meal {
    Meal() {
        print("Meal()");
    }
}

class Bread {
    Bread() {
        print("Bread()");
    }
}

class Cheese {
    Cheese() {
        print("Cheese()");
    }
}

class Lettuce {
    Lettuce() {
        print("Lettuce()");
    }
}

class Lunch extends Meal {
    Lunch() {
        print("Lunch()");
    }
}

class PortableLunch extends Lunch {
    PortableLunch() {
        print("PortableLunch()");
    }
}

public class Sandwich extends PortableLunch {
    private Bread b = new Bread();
    private Cheese c = new Cheese();
    private Lettuce l = new Lettuce();

    public Sandwich() {
        print("Sandwich()");
    }

    public static void main(String[] args) {
        new Sandwich();
    }
}



/* Output:
Meal()
Lunch()
PortableLunch()
Bread()
Cheese()
Lettuce()
Sandwich()
*///:~
