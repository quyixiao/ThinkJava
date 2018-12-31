//: typeinfo/E03_Rhomboid.java
/****************** Exercise 3 ******************
 * Add Rhomboid to Shapes.java. Create a
 * Rhomboid, upcast it to a Shape, then downcast
 * it back to a Rhomboid. Try downcasting to a
 * Circle and see what happens.
 ***********************************************/
package typeinfo;


import java.util.Arrays;
import polymorphism.shape.Circle;
import polymorphism.shape.Shape;
import polymorphism.shape.Square;
import polymorphism.shape.Triangle;

import java.util.Arrays;
import java.util.List;


/*******
 *
 *
 * 318页
 *      修改Shape.java，使这个程序能将某个特定类型的所有形状都 "标示"出来
 *  通过设标志，每一个导出的Shape类的toString()方法应该更够指出Shape是否被标示
 *
 *
 *
 *
 * 1
 *
 */
class Rhomboid extends Shape {
    public String toString() {
        return "Rhomboid";
    }
}

public class E03_Rhomboid {
    public static void main(String[] args) {
        List<Shape> shapes = Arrays.asList(
                new Circle(), new Square(), new Triangle(),
                new Rhomboid()
        );
        for (Shape shape : shapes)
            shape.draw();
        // Upcast to shape:
        Shape shape = new Rhomboid();
        // Downcast to Rhomboid:
        Rhomboid r = (Rhomboid) shape;
        // Downcast to Circle. Succeeds at compile time,
        // but fails at runtime with a ClassCastException:
        Circle c = (Circle)shape;
    }
}