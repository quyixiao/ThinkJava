
package object;


/*****
 * 37页
 * 编写一个程序，展示无论你创建了某个特定类的多少个对象，这个类中的某个特定的static域只有一个实例
 *
 */
public class E06_Storage {
    String s = "Hello, World!";

    int storage(String s) {
        return s.length() * 2;
    }

    void print() {
        System.out.println("storage(s) = " + storage(s));
    }

    public static void main(String[] args) {
        E06_Storage st = new E06_Storage();
        st.print();
    }
}