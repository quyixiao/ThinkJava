package generics;//: generics/LinkedStack.java
// A stack implemented with an internal linked structure.


/***
 *
 *
 *  357
 *
 *
 * 现在我们不用LinkedList，来实现自己的内部链式存储机制
 *
 *
 * 内部类NOde也是一个泛型，它拥有自己的类型参数

 * @param <T>
 *
 *
 * 1
 */
public class LinkedStack<T> {
    private static class Node<U> {
        U item;
        Node<U> next;

        Node() {
            item = null;
            next = null;
        }

        Node(U item, Node<U> next) {
            this.item = item;
            this.next = next;
        }

        boolean end() {
            return item == null && next == null;
        }
    }

    private Node<T> top = new Node<T>(); // End sentinel

    public void push(T item) {
        top = new Node<T>(item, top);
    }

    public T pop() {
        T result = top.item;
        if (!top.end())
            top = top.next;
        return result;
    }

    public static void main(String[] args) {
        LinkedStack<String> lss = new LinkedStack<String>();
        for (String s : "Phasers on stun!".split(" "))
            lss.push(s);
        String s;
        while ((s = lss.pop()) != null)
            System.out.println(s);
    }
}





/* Output:
stun!
on
Phasers
*///:~
