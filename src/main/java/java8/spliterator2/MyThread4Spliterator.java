package java8.spliterator2;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 内部类,线程调用
 */
public class MyThread4Spliterator<T> extends Thread {
    // 寄存变量
    private Spliterator<T> list;

    // 构造 - 传递参数
    public MyThread4Spliterator(Spliterator<T> list) {
        setList(list);


    }

    // 线程调用run
    @Override
    public void run() {
        Spliterator<T> list2 = getList();
        list2.forEachRemaining(new Consumer<T>() {

            @Override
            public void accept(T t) {
                System.out.println(Thread.currentThread().getName() + " === " + t);
            }

        });
    }


    public Spliterator<T> getList() {
        return list;
    }

    public void setList(Spliterator<T> list) {
        this.list = list;
    }
}