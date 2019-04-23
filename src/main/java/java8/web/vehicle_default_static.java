package java8.web;

/***
 * Java 8 的另一个特性是接口可以声明（并且可以提供实现）静态方法。例如：
 */
public interface vehicle_default_static {
    default void print() {
        System.out.println("我是一辆车!");
    }

    // 静态方法
    static void blowHorn() {
        System.out.println("按喇叭!!!");
    }
}
