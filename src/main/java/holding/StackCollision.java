package holding;//: holding/StackCollision.java

/*****
 *
 *
 *
 * 231
 *
 *
 *      Set具有与Collection完全一样的接口，因此没有任何额外的功能，不像前面的两个不同的
 * List,实际上Set就是Collection，只是行为不同，这是继承与多态思想的典型应用，表现不同
 * 的行为，Set是基于对象的值来确定归属性的，而更加复杂的问题我们将在每17章中介绍
 *
 *
 *
 *
 * 1
 *
 *
 */
public class StackCollision {
    public static void main(String[] args) {
        net.mindview.util.Stack<String> stack =
                new net.mindview.util.Stack<String>();
        for (String s : "My dog has fleas".split(" "))
            stack.push(s);
        while (!stack.empty())
            System.out.print(stack.pop() + " ");
        System.out.println();
        java.util.Stack<String> stack2 =
                new java.util.Stack<String>();
        for (String s : "My dog has fleas".split(" "))
            stack2.push(s);
        while (!stack2.empty())
            System.out.print(stack2.pop() + " ");
    }
} /* Output:
fleas has dog My
fleas has dog My
*///:~
