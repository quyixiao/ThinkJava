package polymorphism;


import static net.mindview.util.Print.print;

/*****
 *
 * 164页
 *  在PolyConstructors.java中添加一个RectangularGlyph，并证明出现
 *  本节所描述问题
 *
 *
 *  1
 *
 */

class RectangularGlyph extends Glyph {
    private int width = 4;
    private int height = 5;

    RectangularGlyph(int width, int height) {
        this.width = width;
        this.height = height;
        print("RectangularGlyph.RectangularGlyph(), width = " +
                width + ", height = " + height);
    }

    void draw() {
        print("RectangularGlyph.draw(), area = " + width *
                height);
    }
}


public class E15_PolyConstructors2 {
    public static void main(String[] args) {
        new RoundGlyph(5);
        new RectangularGlyph(2, 2);
    }
}