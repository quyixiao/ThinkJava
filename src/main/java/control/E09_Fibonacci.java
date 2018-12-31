//: control/E09_Fibonacci.java
// {Args: 20}
/****************** Exercise 9 **********************
 * A Fibonacci sequence is the sequence of numbers 1,
 * 1, 2, 3, 5, 8, 13, 21, 34, etc., where each
 * number (from the third on) is the sum of the previous
 * two. Create a method that takes an integer as an
 * argument and displays that many Fibonacci numbers
 * starting from the beginning. If, e.g., you run java
 * Fibonacci 5 (where Fibonacci is the name of the
 * class) the output will be: 1, 1, 2, 3, 5.
 ****************************************************/
package control;


/***
 * * 75页
 * 一个斐波那契数列是由数字1，1，2，3，5，8，13，21，34等等组成的，其中每个数字（从第三个数字起）
 * 都是前两个数字的和，创建一个方法，接受一个整数参数，并显示从第一个元素总共由该参数指定的个数所构成的所有斐波那契数字，
 * 例如，如果运行javaFibonacci 5 其中Fibonacci的类名，那么输出就应该是1，1，2，3，5。
 *
 *
 *
 * 1
 */

public class E09_Fibonacci {
    static int fib(int n) {
        if (n <= 2) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        // Get the max value from the command line:
        int n = 10;
        for (int i = 1; i <= n; i++) {
            System.out.print(fib(i) + ", ");
        }
    }
}