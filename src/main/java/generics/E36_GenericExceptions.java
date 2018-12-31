//: generics/E36_GenericExceptions.java
/****************** Exercise 36 *****************
 * Add a second parameterized exception to the
 * Processor112 class and demonstrate that the exceptions
 * can vary independently.
 ************************************************/
package generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/*****
 *
 * 412页
 *  在processor类中添加第二个参数化异常，并演示这引进异常可互相独立的变化
 *
 *
 *
 * 1
 *
 * @param <T>
 * @param <E>
 * @param <F>
 */
interface
Processor112<T, E extends Exception, F extends Exception> {
    void process(List<T> resultCollector) throws E, F;
}

class
ProcessRunner11<T, E extends Exception, F extends Exception>
        extends ArrayList<Processor112<T, E, F>> {
    List<T> processAll() throws E, F {
        List<T> resultCollector = new ArrayList<T>();
        for (Processor112<T, E, F> processor : this)
            processor.process(resultCollector);
        return resultCollector;
    }
}

class Failure1_11 extends Exception {
}

class Failure1_22 extends Exception {
}

class Processor122 implements
        Processor112<String, Failure1_11, Failure1_22> {
    static Random rnd = new Random(47);
    static int count = 3;

    public void process(List<String> resultCollector)
            throws Failure1_11, Failure1_22 {
        if (count-- > 1)
            resultCollector.add("Hep!");
        else
            resultCollector.add("Ho!");
        if (count < 0)
            if (rnd.nextBoolean())
                throw new Failure1_11();
        throw new Failure1_22();
    }
}

class Failure2_1 extends Exception {
}

class Failure2_2 extends Exception {
}

class Processor2222 implements
        Processor112<Integer, Failure2_1, Failure2_2> {
    static Random rnd = new Random(47);
    static int count = 2;

    public void
    process(List<Integer> resultCollector)
            throws Failure2_1, Failure2_2 {
        if (count-- == 0)
            resultCollector.add(47);
        else {
            resultCollector.add(11);
        }
        if (count < 0)
            if (rnd.nextBoolean())
                throw new Failure2_1();
        throw new Failure2_2();
    }
}

public class E36_GenericExceptions {
    public static void main(String[] args) {
        ProcessRunner11<String, Failure1_11, Failure1_22> runner =
                new ProcessRunner11<String, Failure1_11, Failure1_22>();
        for (int i = 0; i < 3; i++)
            runner.add(new Processor122());
        try {
            System.out.println(runner.processAll());
        } catch (Failure1_11 e) {
            System.out.println(e);
        } catch (Failure1_22 e) {
            System.out.println(e);
        }
        ProcessRunner11<Integer, Failure2_1, Failure2_2> runner2 =
                new ProcessRunner11<Integer, Failure2_1, Failure2_2>();
        for (int i = 0; i < 3; i++)
            runner2.add(new Processor2222());
        try {
            System.out.println(runner2.processAll());
        } catch (Failure2_1 e) {
            System.out.println(e);
        } catch (Failure2_2 e) {
            System.out.println(e);
        }

    }
}