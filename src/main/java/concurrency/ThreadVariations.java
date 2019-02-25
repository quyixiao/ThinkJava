package concurrency;//: concurrency/ThreadVariations.java
// Creating threads with inner classes.

import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;


/****
 *
 *
 * 699页
 *
 * 注意，start()是在构造器中调用的，这个示例相当简单，因此可能是安全的，但是你应该意识到，在构造器中启动线程可能
 *   会变得很有问题，因为另一个任务可能会在构造器结束之前方开始执行，这意味着该任务能够访问处于不稳定的状态的对象，这是
 *   优选Executor而不是显示创建Thread对象的另一个原因。
 *      有时通过使用内部类来将线程代码隐藏在类的中将会很有用的，就像下面一样
 *      InnerThread1创建了一个扩展自Thread的匿名内部类，并且在构造器中创建了这个内部类的一个实例，如果内部类具有你在其他的方法
 *  中需要访问的特殊能力，因此不必创建匿名的内部类，InnerThread2展示了可以替换的方式，在构造器中创建了一个匿名的Thread子类，并且
 *  实现，并且不需要了解该对象确切的类型。
 *      该示例的第三个和第四个类重复了前面的两类，但是它们使用的是Runnable接口而不是Thread类
 *      ThreadMethod类展示了在方法内部如何创建线程，当你准备好了运行线程时，就可能调用这个方法，而在线程开始之后，该方法将返回
 *  ，如果该线程只执行辅助操作，而不是该类重要的操作，那么这与在该类的构造器内部启动线程相比，可能是一种更加的有用而适合的方式
 *      正如前面各节所示，在java中，你可以选择如何实现并发编程，并且这个选择会令人困惑，这个问题通常来自于用来描述并发程序技术的术语
 *  ，的特别是涉及线程的那些。
 *      到目前为止，你应该看到执行的任务与驱动它赋予它的任务，但是线程研究中总是不可变的使用，线程执行这项通过什么，这样的语言，因此，你
 *  得到的印象就是线程就是任务的，当我第一次碰到java线程时，这种印象非常强烈，以至于我们看到一种明显，是一个关系，这就像是在说，很明显我应该人Thread
 *  继承出一个任务，另外，Runnable的名字选择很糟糕，所以我认为Task应该是个很好的名字，如果接口只是其他的方法的返回类型封装，那么他
 *  执行能做什么事情呢，这种命名的方式将是恰当的，但是如果他要表示更高的层的抽象，例如Task，那么概念的名字将会有用。
 *      正如前面各节所示，在java中，你可以选择如何实现并发编程，并且这个选择会令人困惑，这个问题通常是用秋描述并发程序技术的术语，特别是涉及线程的
 *  那些。
 *      到目前为止，你应该看到要执行的任务与驱动它的线程之间有一个差异，这个差异在java类库中尤为明显，因为你对Thread类实际没有任何控制权，并且
 *  这种隔离在使用执行器时更加的明显，因为执行器将替你处理线程的创建和管理，你创建的任务，并通过某种方式将一个线程附着到任务上，以使
 *  得这个线程可驱动任务。
 *      在java中，Thread类自身不执行任何的操作，它只是驱动赋予它的任务，但线程研究中总是不变的使用，"线程执行这项或那项动作"这样的语言，因此
 *      你得到的印象是，线程就是任务，当我第一次碰到java线程时，这种印象非常的强烈，以至于我看到了一各明显的，是一个 关系，这就像是在说，很明显
 *  我应该从Thread继承出一个任务，另外，Runnalble接口的名字选择是很糟糕的，所以我认为Task应该是好得的名字，如果接口只是其方法的返型封装，那么
 *  概念名将有用，
 *      问题是各种抽象级别被混在一起，从概念上讲，我们希望创建独立于其他任务运行的任务，因此我们应该能够定义任务，然后说开始，并且不用操心其细节，但是
 *  在物理上，创建线程可能会代价高昂，因此你必须保存管理它们，这样，从实现的角度看，将任务从线程中分离出来是很有意义的，另外，java的线程机制基于来自
 *  规则，我将在本章中努力演示这些规则
 *      为了澄清这些讨论，我将尝试装在在描述将要执行的工作时使用的术语，任务，只有在我引用到驱动任务的具体机制时，才使用线程，因此，如果你在概念级别
 *  上讨论系统，那就可以只使用任务，而压根不需要提及驱动机制。
 *
 *
 *
 *  注意，Start()是在构造器中调用的，这个示例相当的简单，因此可能是安全的，但是你应该意识到，
 *  在构造器中启动线程可以会变得很有问题，因为另一个任务可能会在构造器中结束之前开始执行，这意味
 *  着该任务能够访问牌不稳定状态的对象，这是优先Executor而不是显式的创建Thread对象的另一个原因
 *
 *
 *  有时通过使用内部类来将线程代码隐藏在类中将会很有用，就不像下面这样
 *
 *
 *
 * InnerThread1创建了一个扩展自Thread的匿名内部类，并且在构造器上创建了这内部类的一个实例，
 * 如果内部类具有你在其他的方法中需要访问特殊能力，那么做将会很有意义，但是在大多数时候，创建线程的
 * 原因只是为了使用Thread的能力，因此不必创建匿名内部类，InnerThread2展示了可替换的方式，在构造器中
 * 创建一个匿名的Thread子类，并且将其身上转型为Thread引用t，如果类中的其他方法需要访问t，那它们可以通过Thread
 * 接口来实现，并且不需要也了解该对象的确切类型，
 *
 *
 *
 *
 *      Threadmethod类展示 了在方法内部如何创建线程，当你准备好运行线程时，就要以调用这个方法
 *      而线程开始之后，该方法将返回，如果该线程只执行辅助操作，而不是该类的重要操作，那么这与在该类
 *      构造器内部启动线程相比，可以是一种更加有用而适合的方式
 *
 *
 *
 *
 *
 *      在Java中，Thread类自身不执行任何操作，它只是驱动赋予它的任务，但是线程研究总是不变的使用，
 *      线程执行这项或那项动作，这样的语言，因此，你得到的印象就是线程就是任务，当我第一次碰Java线程时
 *      ，这种印象非常强烈，以至于
 *
 *
 *
 *      问题是各种抽象被混在一起了，然后说，开始，并且不用操心其细节，但是物理上，创建线程可能会代价高昂
 *      因此，你必须保存并管理它们，这样，从实现的角度看，将任务从线程中分离出来，如果线程在另一个线程t上调用
 *      t.join()，此线程将被挂起，下到目标线程t结束恢复，即t.isAlive()返回为假，
 *
 * 1
 *

 */


// Using a named inner class:
class InnerThread1 {
    private int countDown = 5;
    private Inner inner;

    private class Inner extends Thread {
        Inner(String name) {
            super(name);
            start();
        }

        public void run() {
            try {
                while (true) {
                    print(this);
                    if (--countDown == 0) return;
                    sleep(10);
                }
            } catch (InterruptedException e) {
                print("interrupted");
            }
        }

        public String toString() {
            return getName() + ": " + countDown;
        }
    }

    public InnerThread1(String name) {
        inner = new Inner(name);
    }
}

// Using an anonymous inner class:
class InnerThread2 {
    private int countDown = 5;
    private Thread t;

    public InnerThread2(String name) {
        t = new Thread(name) {
            public void run() {
                try {
                    while (true) {
                        print(this);
                        if (--countDown == 0) return;
                        sleep(10);
                    }
                } catch (InterruptedException e) {
                    print("sleep() interrupted");
                }
            }

            public String toString() {
                return getName() + ": " + countDown;
            }
        };
        t.start();
    }
}

// Using a named Runnable implementation:
class InnerRunnable1 {
    private int countDown = 5;
    private Inner inner;

    private class Inner implements Runnable {
        Thread t;

        Inner(String name) {
            t = new Thread(this, name);
            t.start();
        }

        public void run() {
            try {
                while (true) {
                    print(this);
                    if (--countDown == 0) return;
                    TimeUnit.MILLISECONDS.sleep(10);
                }
            } catch (InterruptedException e) {
                print("sleep() interrupted");
            }
        }

        public String toString() {
            return t.getName() + ": " + countDown;
        }
    }

    public InnerRunnable1(String name) {
        inner = new Inner(name);
    }
}

// Using an anonymous Runnable implementation:
class InnerRunnable2 {
    private int countDown = 5;
    private Thread t;

    public InnerRunnable2(String name) {
        t = new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        print(this);
                        if (--countDown == 0) return;
                        TimeUnit.MILLISECONDS.sleep(10);
                    }
                } catch (InterruptedException e) {
                    print("sleep() interrupted");
                }
            }

            public String toString() {
                return Thread.currentThread().getName() +
                        ": " + countDown;
            }
        }, name);
        t.start();
    }
}

// A separate method to run some code as a task:
class ThreadMethod {
    private int countDown = 5;
    private Thread t;
    private String name;

    public ThreadMethod(String name) {
        this.name = name;
    }

    public void runTask() {
        if (t == null) {
            t = new Thread(name) {
                public void run() {
                    try {
                        while (true) {
                            print(this);
                            if (--countDown == 0) return;
                            sleep(10);
                        }
                    } catch (InterruptedException e) {
                        print("sleep() interrupted");
                    }
                }

                public String toString() {
                    return getName() + ": " + countDown;
                }
            };
            t.start();
        }
    }
}

public class ThreadVariations {
    public static void main(String[] args) {
        new InnerThread1("InnerThread1");
        new InnerThread2("InnerThread2");
        new InnerRunnable1("InnerRunnable1");
        new InnerRunnable2("InnerRunnable2");
        new ThreadMethod("ThreadMethod").runTask();
    }
}




/* (Execute to see output) *///:~
