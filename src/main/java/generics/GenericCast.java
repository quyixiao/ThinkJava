package generics;//: generics/GenericCast.java


/****
 *
 *
 *
 * 402页
 *
 *
 *
 *
 * 使用带有泛型类型参数的转型或instanceof不会有任何效果，下面的容器在内部将各个值存储为Object，
 * 并在获取这些值时，再将它们转型回T
 *
 *
 *
 *
 *
 * 1
 *
 * @param <T>
 */
class FixedSizeStack<T> {
    private int index = 0;
    private Object[] storage;

    public FixedSizeStack(int size) {
        storage = new Object[size];
    }

    public void push(T item) {
        storage[index++] = item;
    }

    public T pop() {
        return (T) storage[--index];
    }
}

public class GenericCast {
    public static final int SIZE = 10;

    public static void main(String[] args) {
        FixedSizeStack<String> strings = new FixedSizeStack<String>(SIZE);
        for (String s : "A B C D E F G H I J".split(" "))
            strings.push(s);
        for (int i = 0; i < SIZE; i++) {
            String s = strings.pop();
            System.out.print(s + " ");
        }
    }
}



/* Output:
J I H G F E D C B A
*///:~
