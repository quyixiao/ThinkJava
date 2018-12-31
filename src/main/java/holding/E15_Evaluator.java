//: holding/E15_Evaluator.java
/****************** Exercise 15 *****************
 * Stacks are often used to evaluate expressions
 * in programming languages. Using
 * net.mindview.util.Stack, evaluate the following
 * expression, where '+' means "push the following
 * letter onto the stack," and '-' means "pop the
 * top of the stack and print it":
 * "+U+n+c---+e+r+t---+a-+i-+n+t+y---+ -+r+u--+l+e+s---"
 ***********************************************/
package holding;

import net.mindview.util.Stack;


/********
 *
 * 231页
 *      栈在编程语言中经常用来对表达式求值，请使用net.mndvew.util.Stack对下面的
 *  表达式求值，其中"+"表示，将后面的字母压进栈，而 "-"表示，"弹出栈顶字母并打印它"：
 *  +U+n+c---+e+r+t---+a-+i-+n+t+y---+ -+r+u--+l+e+s---
 *
 */
public class E15_Evaluator {
    private final static Stack<Character> stack =
            new Stack<Character>();

    private static void evaluate(String expr) {
        char data[] = expr.toCharArray();
        for (int i = 0; i < data.length; )
            switch (data[i++]) {
                case '+':
                    stack.push(data[i++]);
                    break;
                case '-':
                    System.out.print(stack.pop());
            }
    }

    public static void main(String[] args) {
        evaluate(
                "+U+n+c---+e+r+t---+a-+i-+n+t+y---+ -+r+u--+l+e+s---");
    }
}