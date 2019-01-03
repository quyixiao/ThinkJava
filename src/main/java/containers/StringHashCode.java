package containers;//: containers/StringHashCode.java


/****
 * 495
 *
 *
 *
 * 1
 */
public class StringHashCode {
    public static void main(String[] args) {
        String[] hellos = "Hello Hello".split(" ");
        System.out.println(hellos[0].hashCode());
        System.out.println(hellos[1].hashCode());
        String a = "123";
        String b = "123";
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
    }
} /* Output: (Sample)
69609650
69609650
*///:~
