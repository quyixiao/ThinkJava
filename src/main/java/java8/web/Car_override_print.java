package java8.web;


/***
 * 第一个解决方案是创建自己的默认方法，来覆盖重写接口的默认方法：
 */
public class Car_override_print implements vehicle_print, fourWheeler {

    @Override
    public void print() {
        System.out.println("我是一辆四轮汽车!");
    }
}