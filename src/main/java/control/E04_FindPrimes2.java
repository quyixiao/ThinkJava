package control;

import static java.lang.Math.floor;
import static java.lang.Math.sqrt;
import static net.mindview.util.Print.printnb;


/*****
 * 67页
 *  定一个程序，使用两个嵌套的for循环和取余操作符（%）来探测和打印素数
 *  只能被其自身和1整除，而不能被其他数字整除的整数
 *
 *
 * 1
 */
public class E04_FindPrimes2 {
    public static void main(String[] args) {
        int max = 100;
        // Get the max value from the command line,
        // if the argument has been provided:
        if (args.length != 0)
            max = Integer.parseInt(args[0]);
        boolean[] sieve = new boolean[max + 1];
        int limit = (int) floor(sqrt(max));
        System.out.println("=========="+limit);
        printnb(1 + " ");
        if (max > 1)
            printnb(2 + " ");
        // Detect prime numbers
        for (int i = 3; i <= limit; i += 2) {
            System.out.println("++++++++++++++++"+i);
            if (!sieve[i])
                for (int j = 2 * i; j <= max; j += i) {
                    System.out.println("---------------" + j);
                    sieve[j] = true;
                }
        }
        // Print prime numbers
        for (int i = 3; i <= max; i += 2)
            if (!sieve[i])
                printnb(i + " ");
    }
}