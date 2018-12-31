package reusing;


import static net.mindview.util.Print.*;


/***
 *
 *
 *
 * 127页
 * 创建一个简单的类，在第二个类中，将一个引用为第一个对象，运用
 * 惰性初始化来实例化这个对象
 *
 *
 * 1
 */
public class E01_Composition {
    public static void main(String args[]) {
        Second second = new Second("Init String");
        second.check();
        print(second.getSimple());
        second.check();
        print(second); // toString() call
        second.setSimple("New String");
        print(second);
    }
} 