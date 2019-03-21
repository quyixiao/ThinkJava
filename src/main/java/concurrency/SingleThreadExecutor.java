package concurrency;//: concurrency/SingleThreadExecutor.java

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/****
 *    作为另一个示例，假设你有大量的线程，那它们运行的任务将使用文件系统，你可以用SimpleThreadExecutor来运行这些线程，以确保
 * 任意时刻在任何线程中都只有唯一的任务在运行，在这种方式中，你不需要在共享资源上处理同步，（同时不可能过渡的使用文件系统）有
 * 时更好的解决方案是在资源上的同步，你将在本章稍后学习，但是SingleThreadExecutor可以让你省去只是为了维持某些事物的原型而
 * 进行的各种协调努力，通过序列化任务，你可以消除对序列化对象的需求。
 *
 *
 */
public class SingleThreadExecutor {
  public static void main(String[] args) {
    ExecutorService exec = Executors.newSingleThreadExecutor();
    for(int i = 0; i < 5; i++)
      exec.execute(new LiftOff());
    exec.shutdown();
  }
} /* Output:
#0(9), #0(8), #0(7), #0(6), #0(5), #0(4), #0(3), #0(2), #0(1), #0(Liftoff!), #1(9), #1(8), #1(7), #1(6), #1(5), #1(4), #1(3), #1(2), #1(1), #1(Liftoff!), #2(9), #2(8), #2(7), #2(6), #2(5), #2(4), #2(3), #2(2), #2(1), #2(Liftoff!), #3(9), #3(8), #3(7), #3(6), #3(5), #3(4), #3(3), #3(2), #3(1), #3(Liftoff!), #4(9), #4(8), #4(7), #4(6), #4(5), #4(4), #4(3), #4(2), #4(1), #4(Liftoff!),
*///:~
