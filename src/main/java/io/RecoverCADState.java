package io;//: io/RecoverCADState.java
// Restoring the state of the pretend CAD system.
// {RunFirst: StoreCADState}

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;


/*****
 * 585 页
 *
 *
 * 可以看到，xPos，yPos,以及dim的值都视频通话成功的保存和恢复了，但是对static 信息的读取却出现了
 * 问题，所有的读回的颜色应该都是3，但是真实情况却并非如此，CirCle的值为1 ，定义为Red ，而Square的
 * 值为0 ，记住，它们是在构造器中被初始化的，看上去不能按我们期望的方式运行，所以假如想序列化static值，
 * 必须自己手动的实现。
 *
 *
 * 这正是Line中的serializaStaticShape （） 和descrializeStaticState()两个static方法的用途
 * ，可以到，它们是作为存储和读取过程的一部分被显式的调用，注意必须维护写入序列化文件和从该文件中
 * 读回一部分被显式的调用的，注意必须维护写入序列化文件和从该文件中读回顺序，因此，为了使CADState.java
 * 正确的运转起来，我们必须：
 * 1） 为几何形状添加seializeStaticState() 和deserializaStaticState()
 * 2）移除ArrayList shapeTypes() 以及与这有关的所有的代码
 * 3) 在几何形状内添加对新的序列化和反序列化还原静态方法的调用
 *  另一个要注意的问题是安全，因为序列化也会将private 数据保存下来，如果你关心安全问题，那么应将
 *  其标记成transient,但是这之后，还必须设计一种安全的保存信息的方法，以便在执行恢复时可以复位那
 *  些private变量
 *
 *
 *
 */



public class RecoverCADState {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("CADState.out"));
        // Read in the same order they were written:
        List<Class<? extends Shape>> shapeTypes = (List<Class<? extends Shape>>) in.readObject();
        Line.deserializeStaticState(in);
        List<Shape> shapes = (List<Shape>) in.readObject();
        System.out.println(shapes);
    }
}




/* Output:
[class Circlecolor[1] xPos[58] yPos[55] dim[93]
, class Squarecolor[0] xPos[61] yPos[61] dim[29]
, class Linecolor[3] xPos[68] yPos[0] dim[22]
, class Circlecolor[1] xPos[7] yPos[88] dim[28]
, class Squarecolor[0] xPos[51] yPos[89] dim[9]
, class Linecolor[3] xPos[78] yPos[98] dim[61]
, class Circlecolor[1] xPos[20] yPos[58] dim[16]
, class Squarecolor[0] xPos[40] yPos[11] dim[22]
, class Linecolor[3] xPos[4] yPos[83] dim[6]
, class Circlecolor[1] xPos[75] yPos[10] dim[42]
]
*///:~
