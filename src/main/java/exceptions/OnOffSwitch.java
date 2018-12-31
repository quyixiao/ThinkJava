package exceptions;//: exceptions/OnOffSwitch.java
// Why use finally?


/****
 * 266页
 *
 *
 *
 *
 * 1
 *
 */
public class OnOffSwitch {
    private static Switch sw = new Switch();

    public static void f() throws OnOffException1, OnOffException2 {
        System.out.println("f()");
        throw new OnOffException1();
    }

    public static void main(String[] args) {
        try {
            sw.on();
            // Code that can throw exceptions...
            f();
            sw.off();
        } catch (OnOffException1 e) {
            System.out.println("OnOffException1");
            sw.off();
        } catch (OnOffException2 e) {
            System.out.println("OnOffException2");
            sw.off();
        }
    }
} /* Output:
on
off
*///:~
