//: enumerated/RoShamBo2.java
// Switching one enum on another.
package enumerated;

import static enumerated.Outcome.*;


/**
 * 615 页
 *
 *
 * Item 这几种类型的接口，将会被用作多路分发，BoShamBo1.match() 有两个Item参数通过调用Item.compete()方法
 * 开始两路分发，要判定a的类型，分发机制会在a的实际类型的compete()内部起到分发的作用，compete() 方法通过调用
 * eval() 来为另一个类型实现第二次分发，将自身的信息，当第一个做为参数类型实现第二次分发，将自身的类型信息，当
 * 第二次发完成时，你就能够知道两个Item 对象的具体类型了。
 *
 * 要配置好多路分发需要很多的工序，不过要记住，它的好处在于方法调用时充分优雅的代码对你们确实有重要的意义。
 *
 * 直接将RoshamRo1.java 翻译为基于enum的版本是有问题的，因为enum实例不是类型，不能将enum实例你为参数的类型。
 * 所以作为参数的类型，所以无法重载eval()方法，不过，还有很多的方式可以实现多路分发，并从enum中获益
 *
 *
 */
public enum RoShamBo2 implements Competitor<RoShamBo2> {
  PAPER(DRAW, LOSE, WIN),
  SCISSORS(WIN, DRAW, LOSE),
  ROCK(LOSE, WIN, DRAW);
  private Outcome vPAPER, vSCISSORS, vROCK;
  RoShamBo2(Outcome paper, Outcome scissors, Outcome rock) {
    this.vPAPER = paper;
    this.vSCISSORS = scissors;
    this.vROCK = rock;
  }	
  public Outcome compete(RoShamBo2 it) {
    switch(it) {
      default:
      case PAPER: return vPAPER;
      case SCISSORS: return vSCISSORS;
      case ROCK: return vROCK;
    }
  }
  public static void main(String[] args) {
    RoShamBo.play(RoShamBo2.class, 20);
  }
}




/* Output:
ROCK vs. ROCK: DRAW
SCISSORS vs. ROCK: LOSE
SCISSORS vs. ROCK: LOSE
SCISSORS vs. ROCK: LOSE
PAPER vs. SCISSORS: LOSE
PAPER vs. PAPER: DRAW
PAPER vs. SCISSORS: LOSE
ROCK vs. SCISSORS: WIN
SCISSORS vs. SCISSORS: DRAW
ROCK vs. SCISSORS: WIN
SCISSORS vs. PAPER: WIN
SCISSORS vs. PAPER: WIN
ROCK vs. PAPER: LOSE
ROCK vs. SCISSORS: WIN
SCISSORS vs. ROCK: LOSE
PAPER vs. SCISSORS: LOSE
SCISSORS vs. PAPER: WIN
SCISSORS vs. PAPER: WIN
SCISSORS vs. PAPER: WIN
SCISSORS vs. PAPER: WIN
*///:~
