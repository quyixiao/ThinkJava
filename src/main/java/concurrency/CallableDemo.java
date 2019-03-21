package concurrency;//: concurrency/CallableDemo.java

import java.util.ArrayList;
import java.util.concurrent.*;


/****
 *
 *     658页
 *
 *     Runnable是执行工作的独立任务，但是它不返回任何值，如果你希望任务在完成时能够返回一个值，那么可以实现
 * Callable接口而不是Runnable接口，在Java SEt 5 中引入的Callabel是一种具有类型的参数泛型，它的类型参数
 * 表示的是从方法的Call() 而不是run() 中返回的值，并且必须使用ExecutorService.submit()方法调用它，下面
 * 是一个简单的示例。
 *      summit()方法会产生Futrue对象，它用Callable返回结果的特定类型进行了参数化，你可能用isDone()方法来查询
 *  调用get()方法，在这种情况下，get() 将阻塞，直至结果准备就绪，你还可以在试图用get()方法来获得结果之前 ，先调用
 *  具有超时的get()或者调用isDone()来查看任务是否完成。
 *
 *
 *
 *
 * 1
 */
class TaskWithResult implements Callable<String> {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    public String call() {
        return "result of TaskWithResult " + id;
    }
}

public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();


        for (int i = 0; i < 10; i++) {
            results.add(exec.submit(new TaskWithResult(i)));
        }



        for (Future<String> fs : results)
            try {
                // get() blocks until completion: 先调用具有超时的get()，或者调用isDone()来查看任务是否完成

                // 修改练习，使得计算所有的斐波纳契数字的数值总和的任务成为callable，创建多个任务并显示结果

                System.out.println(fs.get());
            } catch (InterruptedException e) {
                System.out.println(e);
                return;
            } catch (ExecutionException e) {
                System.out.println(e);
            } finally {
                exec.shutdown();
            }
    }
}







/* Output:
result of TaskWithResult 0
result of TaskWithResult 1
result of TaskWithResult 2
result of TaskWithResult 3
result of TaskWithResult 4
result of TaskWithResult 5
result of TaskWithResult 6
result of TaskWithResult 7
result of TaskWithResult 8
result of TaskWithResult 9
*///:~
