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
 *
 *
 *  一个任务有可能在另一个任务执行第一个对currentEvenValue的递增操作之后，但是没有执行第二个操作，
 *  调用next()方法即代码中被注释为Danger Point here 在
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
