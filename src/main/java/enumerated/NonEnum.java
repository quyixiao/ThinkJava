package enumerated;//: enumerated/NonEnum.java


/***
 * 595 页
 *
 *
 * 因为getEnumConstants()是Class上的方法，所以你甚至可以对不是枚举类调用此方法
 *
 *
 *
 * 1
 *
 *
 * 只不过，此时方法返回null,所以当你试图使用其返回的结果时会发生异常
 *
 *
 *
 *
 *
 */
public class NonEnum {
    public static void main(String[] args) {
        Class<Integer> intClass = Integer.class;
        try {
            for (Object en : intClass.getEnumConstants()) {
                System.out.println(en);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


/* Output:
java.lang.NullPointerException
*///:~
