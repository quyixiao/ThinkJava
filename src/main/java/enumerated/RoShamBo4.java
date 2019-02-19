//: enumerated/RoShamBo4.java
package enumerated;


/**
 *
 *
 * 618 页
 *
 *
 * 虽然这种方式可能工作，但是不合理，如果采用RoShamBo2.java的解决方案，那么在添加一个新的类型时，只需要更少的代码，而且也更直接
 *
 *
 * 其中，具有两个参数的compete()方法执行第二个参数，该方法执行一系列的比较，其行为类似switch语句，这个版本的程序更简短，不过
 * 却比较难以理解，对于一个系统而言，难以理解有的代码斗争导致整个系统不够健壮
 *
 *
 * 
 *
 *
 * 1
 *
 *
 *
 *
 *
 *
 *
 */
public enum RoShamBo4 implements Competitor<RoShamBo4> {
    ROCK {
        public Outcome compete(RoShamBo4 opponent) {
            return compete(SCISSORS, opponent);
        }
    },
    SCISSORS {
        public Outcome compete(RoShamBo4 opponent) {
            return compete(PAPER, opponent);
        }
    },
    PAPER {
        public Outcome compete(RoShamBo4 opponent) {
            return compete(ROCK, opponent);
        }
    };

    Outcome compete(RoShamBo4 loser, RoShamBo4 opponent) {
        return ((opponent == this) ? Outcome.DRAW
                : ((opponent == loser) ? Outcome.WIN
                : Outcome.LOSE));
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo4.class, 20);
    }
} /* Same output as RoShamBo2.java *///:~
