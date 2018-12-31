//: enumerated/Burrito.java
package enumerated;


/**
 * 591 页
 *
 *
 *
 * 使用 static import 能够将enum实例的标识符带入当前的命名空间，所以无需用enum类型来修饰enum实例，
 * 这是一个好的想法吗，或者还是显式地修饰enum实例会更加的好，这要看代码的会不会导致你的代码令人难以理解
 * ，多数情况下，使用static import 还是有好处的，不过这个程序员还是要对具体的情况，具体分析。
 * 注意：在定义enum同一个文件中，这种技巧无法使用，如果是在默认的包中定义enum，这种方法技巧无法使用（
 * sum 内部对这一点显然有不同的意见）
 *
 *
 *
 *
 *
 *
 */

import static enumerated.Spiciness.*;

public class Burrito {
    Spiciness degree;

    public Burrito(Spiciness degree) {
        this.degree = degree;
    }

    public String toString() {
        return "Burrito is " + degree;
    }

    public static void main(String[] args) {
        System.out.println(new Burrito(NOT));
        System.out.println(new Burrito(MEDIUM));
        System.out.println(new Burrito(HOT));
    }
}




/* Output:
Burrito is NOT
Burrito is MEDIUM
Burrito is HOT
*///:~
