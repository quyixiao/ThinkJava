package initialization;//: initialization/EnumOrder.java


/******
 *
 * 106页
 *
 *
 *
 * 1
 *
 *      编译器还会创建ordinal()方法，用来表示某个特定enum常量的声明顺序，以及static values()方法
 *  用来按照enum常量声明顺序，产生由这些常量值构成的数组
 *
 */
public class EnumOrder {
    public static void main(String[] args) {
        for (Spiciness s : Spiciness.values())
            System.out.println(s + ", ordinal " + s.ordinal());
    }
}



/* Output:
NOT, ordinal 0
MILD, ordinal 1
MEDIUM, ordinal 2
HOT, ordinal 3
FLAMING, ordinal 4
*///:~
