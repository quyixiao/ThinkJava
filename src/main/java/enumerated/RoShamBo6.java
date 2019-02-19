//: enumerated/RoShamBo6.java
// Enums using "tables" instead of multiple dispatch.
package enumerated;

import static enumerated.Outcome.*;


/****
 *
 * 619
 * 最快速的，虽然我们简短enumMap内部其实是要不用数组实现的
 *
 * table与前一个例子中的initRow()方法的调用次序完全相同
 *
 *
 *
 *
 * 1
 *
 *
 *
 *
 */
enum RoShamBo6 implements Competitor<RoShamBo6> {
    PAPER, SCISSORS, ROCK;
    private static Outcome[][] table = {
            {DRAW, LOSE, WIN}, // PAPER
            {WIN, DRAW, LOSE}, // SCISSORS
            {LOSE, WIN, DRAW}, // ROCK
    };

    public Outcome compete(RoShamBo6 other) {
        return table[this.ordinal()][other.ordinal()];
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo6.class, 20);
    }
} ///:~
