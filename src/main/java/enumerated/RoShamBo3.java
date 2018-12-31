//: enumerated/RoShamBo3.java
// Using constant-specific methods.
package enumerated;

import static enumerated.Outcome.*;


/****
 *
 *
 * 617 页
 *
 *  常量相关的方法允许我们为每个enum实例提供方法的不同的实现，这使得我常量相关的方法似乎实现了多路
 *  分发的完美的解决方案，不过，通过这种方式，enum实现虽然可能具有不同的行为，但它们仍然不是类型
 *  ，不能将其作为方法签名中的参数类型来使用，最好的办法是将enum用在swithch中
 *
 *
 */
public enum RoShamBo3 implements Competitor<RoShamBo3> {
    PAPER {
        public Outcome compete(RoShamBo3 it) {
            switch (it) {
                default: // To placate the compiler
                case PAPER:
                    return DRAW;
                case SCISSORS:
                    return LOSE;
                case ROCK:
                    return WIN;
            }
        }
    },
    SCISSORS {
        public Outcome compete(RoShamBo3 it) {
            switch (it) {
                default:
                case PAPER:
                    return WIN;
                case SCISSORS:
                    return DRAW;
                case ROCK:
                    return LOSE;
            }
        }
    },
    ROCK {
        public Outcome compete(RoShamBo3 it) {
            switch (it) {
                default:
                case PAPER:
                    return LOSE;
                case SCISSORS:
                    return WIN;
                case ROCK:
                    return DRAW;
            }
        }
    };

    public abstract Outcome compete(RoShamBo3 it);

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo3.class, 20);
    }
} /* Same output as RoShamBo2.java *///:~
