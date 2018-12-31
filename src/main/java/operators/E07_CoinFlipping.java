package operators;

import java.util.Random;

/***
 * 46页
 * 编写一个程序，模拟扔硬币的结果
 *
 *
 * 1
 *
 */
public class E07_CoinFlipping {
    public static void main(String[] args) {
        Random rand = new Random(47);
        boolean flip = rand.nextBoolean();
        System.out.print("OUTCOME: ");
        System.out.println(flip ? "HEAD" : "TAIL");
    }
}