package concurrency;//: concurrency/EvenGenerator.java
// When threads collide.


/*****
 *
 *
 * 678 页
 *
 * 同步控制EvenGenerator
 *    通过EvenGenerator.java中加入sychronized关键字，可以防止不希望的线程访问
 *    对Thread.yeeld()调用被插入到了两递增操作之间，以提高在currentEvenValue是奇数状态时上下文区切换的可能性，因为互斥可以防止多个任务同时进入
 * 临界区，所以这不会产生任何失败，但是失败将会发生，调用yiedld()是一种促使其发生的有效方式。
 *
 */
public class EvenGenerator extends IntGenerator {
  private int currentEvenValue = 0;
  public int next() {
    ++currentEvenValue; // Danger point here!
    ++currentEvenValue;
    return currentEvenValue;
  }
  public static void main(String[] args) {
    EvenChecker.test(new EvenGenerator());
  }
} /* Output: (Sample)
Press Control-C to exit
89476993 not even!
89476993 not even!
*///:~
