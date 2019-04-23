package java8.web;


/***
 * 第二种解决方案可以使用 super 来调用指定接口的默认方法：
 */
public class Car_override_super_print implements vehicle_print, fourWheeler {
    @Override
    public void print() {
        vehicle_print.super.print();
    }
}