package containers;//: containers/FailFast.java
// Demonstrates the "fail-fast" behavior.

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 *
 *
 * 517
 *
 *
 * 程序运行时发生的异常，因为在容器取得迭代器之后，又有东西被放入到了该容器中，当程序的不同
 * 部分修改一个被器时，就可能导致容器的状态不一致，所以，此异常提醒你，应该修改代码，在此
 * 例中，应该添加完元素之后，再获取迭代器
 *
 *
 * 1
 *
 *
 *
 */
public class FailFast {
    public static void main(String[] args) {
        Collection<String> c = new ArrayList<String>();

        Iterator<String> it = c.iterator();


        c.add("An object");


        String s = it.next();
    }
} /* Output:
java.util.ConcurrentModificationException
*///:~
