package containers;//: containers/Stacks.java
// Demonstration of Stack Class.

import java.util.LinkedList;
import java.util.Stack;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;


/**
 * 522页
 * <p>
 * <p>
 * <p>
 * <p>
 * String表示是从Month enum常量中生成的，用push()插入Stack，然后再从栈的顶端弹出来
 * 用pop()，这里特别要强调的是，可以在Stack对象上执行Vector操作，这不会有任何问题，因为
 * 继承的作用使得Stack是一个Vector，因此所有的可以对Vector执行的操作，都可以对Stack执行
 * 例如 ，elementAt()
 * <p>
 *
 *
 *
 * 前面曾经说过，如果需要栈的行为，应该使用LinkedList,或者从LinkedList类中创建一个net.indview.util.Stack类
 *
 *
 *
 *
 * 1
 */
enum Month {
    JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE,
    JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER
}

public class Stacks {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        for (Month m : Month.values())
            stack.push(m.toString());
        print("stack = " + stack);
        // Treating a stack as a Vector:
        stack.addElement("The last line");
        print("element 5 = " + stack.elementAt(5));
        System.out.println("====================================================");
        print("popping elements:");
        while (!stack.empty()) {
            printnb(stack.pop() + " ");
        }
        System.out.println();


        System.out.println("============");

        // Using a LinkedList as a Stack:
        LinkedList<String> lstack = new LinkedList<String>();
        for (Month m : Month.values()) {
            lstack.addFirst(m.toString());
        }
        print("lstack = " + lstack);

        while (!lstack.isEmpty())
            printnb(lstack.removeFirst() + " ");

        // Using the Stack class from
        // the Holding Your Objects Chapter:
        net.mindview.util.Stack<String> stack2 =
                new net.mindview.util.Stack<String>();


        System.out.println();
        System.out.println("------------------");
        for (Month m : Month.values()) {
            System.out.println(m.toString());
            stack2.push(m.toString());
        }
        print("stack2 = " + stack2);

        System.out.println();
        while (!stack2.empty()) {
            printnb(stack2.pop() + " ");
        }

    }
}




/* Output:
stack = [JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER]
element 5 = JUNE
popping elements:
The last line NOVEMBER OCTOBER SEPTEMBER AUGUST JULY JUNE MAY APRIL MARCH FEBRUARY JANUARY lstack = [NOVEMBER, OCTOBER, SEPTEMBER, AUGUST, JULY, JUNE, MAY, APRIL, MARCH, FEBRUARY, JANUARY]
NOVEMBER OCTOBER SEPTEMBER AUGUST JULY JUNE MAY APRIL MARCH FEBRUARY JANUARY stack2 = [NOVEMBER, OCTOBER, SEPTEMBER, AUGUST, JULY, JUNE, MAY, APRIL, MARCH, FEBRUARY, JANUARY]
NOVEMBER OCTOBER SEPTEMBER AUGUST JULY JUNE MAY APRIL MARCH FEBRUARY JANUARY
*///:~
