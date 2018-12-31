//: generics/E05_LinkedStack2.java
/****************** Exercise 5 *****************
 * Remove the type parameter on the Node class and
 * modify the rest of the code in LinkedStack.java
 * to show that an inner class has access to the
 * generic type parameters of its outer class.
 ************************************************/
package generics;


/*****
 *
 *  357页
 * 移除Node类上的类型参数，并修改LinkedStack.java代码，证明内部类
 * 可以访问其外部类的类型参数
 *
 *
 *
 * 1
 * @param <T>
 */
class LinkedStack1<T> {
    private class Node {
        T item;
        Node next;

        Node() {
            item = null;
            next = null;
        }

        Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }

        boolean end() {
            return item == null && next == null;
        }
    }

    private Node top = new Node(); // End sentinel

    public void push(T item) {
        top = new Node(item, top);
    }

    public T pop() {
        Object result = top.item;
        if (!top.end())
            top = top.next;
        return (T) result;
    }
}

public class E05_LinkedStack2 {
    public static void main(String[] args) {
        LinkedStack1<String> lss = new LinkedStack1<String>();
        for (String s : "Phasers on stun!".split(" "))
            lss.push(s);
        String s;
        while ((s = lss.pop()) != null)
            System.out.println(s);
    }
}