package java8.web;

import java.util.Arrays;
import java.util.List;

class Car1 {
    @FunctionalInterface
    public interface Supplier<T> {
        T get();
    }

    //Supplier是jdk1.8的接口，这里和lamda一起使用了
    public static Car1 create(final Supplier<Car1> supplier) {
        return supplier.get();
    }

    public static void collide(final Car1 car) {
        System.out.println("Collided " + car.toString());
    }

    public void follow(final Car1 another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

    public static void main(String[] args) {
        //构造器引用：它的语法是Class::new，或者更一般的Class< T >::new实例如下：
        Car1 car = Car1.create(Car1::new);
        Car1 car1 = Car1.create(Car1::new);
        Car1 car2 = Car1.create(Car1::new);
        Car1 car3 = new Car1();
        List<Car1> cars = Arrays.asList(car, car1, car2, car3);
        System.out.println("===================构造器引用========================");
        //静态方法引用：它的语法是Class::static_method，实例如下：
        cars.forEach(Car1::collide);
        System.out.println("===================静态方法引用========================");
        //特定类的任意对象的方法引用：它的语法是Class::method实例如下：
        cars.forEach(Car1::repair);
        System.out.println("==============特定类的任意对象的方法引用================");
        //特定对象的方法引用：它的语法是instance::method实例如下：
        final Car1 police = Car1.create(Car1::new);
        cars.forEach(police::follow);
        System.out.println("===================特定对象的方法引用===================");

    }
}
