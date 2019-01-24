package enumerated;//: enumerated/UpcastEnum.java
// No values() method if you upcast an enum

enum Search {HITHER, YON}


/**
 *
 *
 * 595 页
 *
 * 由于values() 方法是由编译器插入Enum到enum定义中的static方法，所以如果 你将enum实例向上转型为
 * Enum，那么()方法就不访问了，不过在，Class中有一个getEnumConstants()方法，所以即便Enum接口中没有
 * values()方法，我们仍然可能通过Class对象取得所有的enum实例
 *
 *
 *
 * 1

 *
 */
public class UpcastEnum {
    public static void main(String[] args) {
        Search[] vals = Search.values();
        Enum e = Search.HITHER; // Upcast
        // e.values(); // No values() in Enum
        for (Enum en : e.getClass().getEnumConstants()) {
            System.out.println(en);
        }
    }
}




/* Output:
HITHER
YON
*///:~
