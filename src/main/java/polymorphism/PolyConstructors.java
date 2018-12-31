package polymorphism;//: polymorphism/PolyConstructors.java
// Constructors and polymorphism
// don't produce what you might expect.

import static net.mindview.util.Print.print;


/***
 * 163页
 *
 *  1
 *
 *
 *  在其他任何事物发生之前，将分配给对象的存储空间初始化成二进制的零
 *  如前所述建新调用基类构造器，此时，调用被覆盖后的draw()方法，要在调用
 *  RountdGlyph构造器之前调用，由于步骤一的缘故，我们此时会发现radius的值为0
 *  调用导出类的构造器主体
 *
 *
 */
class Glyph {
    void draw() {
        print("Glyph.draw()");
    }

    Glyph() {
        print("Glyph() before draw()");
        draw();
        print("Glyph() after draw()");
    }
}

class RoundGlyph extends Glyph {
    private int radius = 1;

    RoundGlyph(int r) {
        radius = r;
        print("RoundGlyph.RoundGlyph(), radius = " + radius);
    }

    void draw() {
        print("RoundGlyph.draw(), radius = " + radius);
    }
}

public class PolyConstructors {
    public static void main(String[] args) {
        new RoundGlyph(5);
    }
} /* Output:
Glyph() before draw()
RoundGlyph.draw(), radius = 0
Glyph() after draw()
RoundGlyph.RoundGlyph(), radius = 5
*///:~
