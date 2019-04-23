package java8.web;

/***
 * 一个接口有默认方法，考虑这样的情况，一个类实现了多个接口，且这些接口有相同的默认方法，以下实例说明了这种情况的解决方法：
 */
public interface fourWheeler {
    default void print() {
        System.out.println("我是一辆四轮车!");
    }
}