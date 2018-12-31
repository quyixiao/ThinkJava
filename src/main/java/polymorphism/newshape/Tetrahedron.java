package polymorphism.newshape;

import static net.mindview.util.Print.print;


/****8
 *  以练习1 为基础，在Cycle中添加wheels()方法轮子的数量，修改ride()方法，
 *  让它调用wheels()方法，并验证多态起作用了
 *
 *
 */
public class Tetrahedron extends Shape {
    public void draw() {
        print("Tetrahedron.draw()");
    }

    public void erase() {
        print("Tetrahedron.erase()");
    }

    public void msg() {
        print("Tetrahedron.msg()");
    }
} 