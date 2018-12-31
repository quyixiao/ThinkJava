package holding;

import java.util.LinkedList;
import java.util.Queue;


/******
 *
 *  237页
 *      写一个称为Command的类，它包含一个String域和一个显示该String的operation()方法
 *  ，写第二个类，它具有一个使用Command对象来填充一个Queue并返回这个对象的方法，将填充后
 *  的Queue传递给第三类的一个方法，该方法消耗掉Queue中的对象，并调用用
 *  它们的operation()方法
 *
 *
 *  1
 *
 *
 */
class Command {
    private final String cmd;

    Command(String cmd) {
        this.cmd = cmd;
    }

    public void operation() {
        System.out.println(cmd);
    }
}

class Producer {
    public static void produce(Queue<Command> q) {
        q.offer(new Command("load"));
        q.offer(new Command("delete"));
        q.offer(new Command("save"));
        q.offer(new Command("exit"));
    }
}

class Consumer {
    public static void consume(Queue<Command> q) {
        while (q.peek() != null)
            q.remove().operation();
    }
}

public class E27_CommandQueue {
    public static void main(String[] args) {
        Queue<Command> cmds = new LinkedList<Command>();
        Producer.produce(cmds);
        Consumer.consume(cmds);
    }
}