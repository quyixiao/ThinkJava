package object;//: object/ShowProperties.java


/****
 * 31页
 *
 */
public class ShowProperties {
    public static void main(String[] args) {
        System.getProperties().list(System.out);
        System.out.println("==========================");
        System.out.println(System.getProperty("user.name"));
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println(
                System.getProperty("java.library.path"));
    }
} ///:~
