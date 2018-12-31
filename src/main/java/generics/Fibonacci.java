package generics;//: generics/Fibonacci.java
// Generate a Fibonacci sequence.

import net.mindview.util.Generator;


/***
 *
 *
 * 360
 *
 * 下面是一个Generator<T>接口的另一个实现，它钢表生成Fibonacci数列
 *
 *
 *
 * 虽然我们Fibonacci的类里外外使用的都是int类型，但是其类型却是Integer，这个例子引出了Java泛型的
 * 一个局限性，基本类型无法作为类型参数，不过，JavaSe5具备了自动打包和自动拆包的功能，可以很
 * 方便的在基本类型和其相应假装器类型之间转换，通过这个例子中，Fibonacci类对int的使用，我们己经看到了这样的一个效果
 *
 *
 *
 *
 *
 *
 * 1
 */
public class Fibonacci implements Generator<Integer> {
    private int count = 0;

    public Integer next() {
        return fib(count++);
    }

    private int fib(int n) {
        if (n < 2) return 1;
        return fib(n - 2) + fib(n - 1);
    }

    public static void main(String[] args) {
        Fibonacci gen = new Fibonacci();
        for (int i = 0; i < 18; i++)
            System.out.print(gen.next() + " ");
    }
} /* Output:
1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584
*///:~
