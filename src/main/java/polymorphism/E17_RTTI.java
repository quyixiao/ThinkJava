package polymorphism;

import polymorphism.cycle.Cycle;
import polymorphism.cycle.Tricycle;
import polymorphism.cycle3.Bicycle;
import polymorphism.cycle3.Unicycle;


/******
 * 168页
 * 使用练习1中的Cycle的层次结构 ，在Uniccycle和Bicycle中添加balance()方法
 * 而Tricyle中不添加，创建所有的这三种类型的实例，并将它们身上孝思不匮为Cycle数组
 * ，在该数组的每一个元素上的都尝试调用balance()，并观察结果，然后将它们向下转型，
 * 再次调用balance()并观察将产生什么
 *
 */
public class E17_RTTI {
    public static void main(String[] args) {
        Cycle[] cycles = new Cycle[]{new Unicycle(),
                new Bicycle(), new Tricycle()};
        // Compile time: method not found in Cycle:
        // cycles[0].balance();
        // cycles[1].balance();
        // cycles[2].balance();
        ((Unicycle) cycles[0]).balance();  // Downcast/RTTI
        ((Bicycle) cycles[1]).balance();   // Downcast/RTTI
        ((Unicycle) cycles[2]).balance();  // Exception thrown
    }
} ///:~
//: polymorphism/cycle3/Unicycle.java
///:~
//: polymorphism/cycle3/Bicycle.java
