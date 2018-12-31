//: strings/E03_TurtleRedirected.java
/****************** Exercise 3 ******************
 * Modify Turtle.java so that it sends all output
 * to System.err.
 ***********************************************/
package strings;

import java.util.Formatter;


/******
 *
 * 291
 * 修改Turtle.java使之将结果输出到  System.err中
 *
 *
 * 1
 *
 */
class Turtle1 {
    private final String name;
    private final Formatter f;

    public Turtle1(String name, Formatter f) {
        this.name = name;
        this.f = f;
    }

    public void move(int x, int y) {
        f.format("%s The Turtle is at (%d,%d)\n", name, x, y);
    }
}

public class E03_TurtleRedirected {
    public static void main(String[] args) {
        Formatter f = new Formatter(System.err);
        Turtle1 tommy = new Turtle1("Tommy", f),
                terry = new Turtle1("Terry", f);
        tommy.move(0, 0);
        terry.move(4, 8);
        tommy.move(3, 4);
        terry.move(2, 5);
        tommy.move(3, 3);
        terry.move(3, 3);
    }
}