//: annotations/Multiplier.java
// APT-based annotation processing.
package annotations;


/***
 * 在multiplier类中，它只对正整数起作用，有一个multiply()方法，该方法多次调用了一个私有的
 * add()方法以实现乘法操作，add()方法不是公共的，因此不将其作为接口的一部分，注解给出了的值Imuiltipller，这就是
 * 将要生成的接口的名字。
 * apt工具需要一个工厂类来为其指明正确的处理器，然后它才能调用处理器上的process() 方法。
 *
 *
 */
@ExtractInterface("IMultiplier")
public class Multiplier {
    public int multiply(int x, int y) {
        int total = 0;
        for (int i = 0; i < x; i++)
            total = add(total, y);
        return total;
    }

    private int add(int x, int y) {
        return x + y;
    }

    public static void main(String[] args) {
        Multiplier m = new Multiplier();
        System.out.println("11*16 = " + m.multiply(11, 16));
    }
} /*
     * Output: 11*16 = 176
	 */// :~
