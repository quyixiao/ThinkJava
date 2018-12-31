package interfaces;//: interfaces/RandomDoubles.java

import java.util.Random;


/**
 *
 * 183页
 * 我样再次使用了适配器模式，但是在本例中，被适本配的类可以通过继承和实现Readable接口来创建，因此，通过使用interface关键字提供的
 * 伪多重继承机制，我们可以生成
 *
 *
 */
public class RandomDoubles {
    private static Random rand = new Random(47);

    public double next() {
        return rand.nextDouble();
    }

    public static void main(String[] args) {
        RandomDoubles rd = new RandomDoubles();
        for (int i = 0; i < 7; i++)
            System.out.print(rd.next() + " ");
    }
} /* Output:
0.7271157860730044 0.5309454508634242 0.16020656493302599 0.18847866977771732 0.5166020801268457 0.2678662084200585 0.2613610344283964
*///:~
