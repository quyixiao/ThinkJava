//: polymorphism/E02_Shapes.java
/****************** Exercise 2 ****************
 * Add the @Override annotation to the shapes
 * example.
 ***********************************************/
package polymorphism;

import polymorphism.shape.Shape;


/****
 * 150页
 * 创建一个cycle类，它具有子类 ，Unicycle,Bicycle和Tricycle，演示每一个
 * 类型的实例都可以经由ride()方法身上转型为Cycle
 *
 * 1
 */
public class E02_Shapes {

    private static RandomShapeGenerator gen = new RandomShapeGenerator();

    public static void main(String[] args) {
        Shape[] shapes = new Shape[9];
        // Fill up the array with shapes:
        for (int i = 0; i < shapes.length; i++) {
            shapes[i] = gen.next();
        }
        // Make polymorphic method calls:
        for (Shape shape : shapes) {
            shape.draw();
        }
    }
}