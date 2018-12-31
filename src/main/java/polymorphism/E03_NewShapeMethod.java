//: polymorphism/E03_NewShapeMethod.java


/****************** Exercise 3 *****************
 * Add a new method in the base class of
 * Shapes.java that prints a message, but don't
 * override it in the derived classes. Explain
 * what happens. Now override it in only one of the
 * derived classes and see what happens. Finally,
 * override it in all the derived classes.
 ***********************************************/
package polymorphism;


/*****
 *
 *
 * 153 页
 *  在基类Shapes.java中添加一个新的方法，用于打印一条消息，但展示出类中不要覆盖这个方法。
 *  请解释发生了什么，现在，在其中一个导出类中边覆盖该方法，而在其他的导出类
 *  中不予覆盖，观察又发生，最后，在所有的导出类中覆盖这个方法
 *
 *
 * 1
 */

import polymorphism.newshape.Circle;
import polymorphism.newshape.Shape;
import polymorphism.newshape.Square;
import polymorphism.newshape.Triangle;


public class E03_NewShapeMethod {
    public static void main(String args[]) {
        Shape[] shapes = {new Circle(), new Square(), new Triangle(),};
        // Make polymorphic method calls:
        for (Shape shape : shapes) {
            shape.draw();
            shape.erase();
            shape.msg();
        }
    }
}