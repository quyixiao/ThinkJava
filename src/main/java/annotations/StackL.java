//: annotations/StackL.java
// A stack built on a linkedList.
package annotations;

import java.util.LinkedList;


/***
 * 641 页
 *
 * 泛型为@Unit出了一个难题，因为我们不可能"泛泛地测试"。我们必须针对某个特定类型的参数基参数集才能进行测试，
 * 解决办法很简单：这测试类型继承自泛型的一个特定版本即可 。
 *
 *
 *
 *
 * @param <T>
 */
public class StackL<T> {
    private LinkedList<T> list = new LinkedList<T>();

    public void push(T v) {
        list.addFirst(v);
    }

    public T top() {
        return list.getFirst();
    }

    public T pop() {
        return list.removeFirst();
    }
} ///:~
