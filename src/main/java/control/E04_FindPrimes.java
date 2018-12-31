package control;


/*****
 * 67页
 *  写一个程序，使用两个嵌套的for循环和取余操作符（%）来探测和打印素数只能被其自身和1整除，
 *  而不能被其他数字整除的整数
 *
 *
 *  1
 *
 */
public class E04_FindPrimes {
    public static void main(String[] args) {
        int max = 100;
        // Get the max value from the command line,
        // if the argument has been provided:
        if (args.length != 0)
            max = Integer.parseInt(args[0]);
        for (int i = 1; i < max; i++) {
            boolean prime = true;
            for (int j = 2; j < i; j++)
                if (i % j == 0)
                    prime = false;
            if (prime)
                System.out.print(i + " ");
        }
    }
} 