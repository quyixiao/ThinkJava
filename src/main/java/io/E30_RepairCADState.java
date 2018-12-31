//: io/E30_RepairCADState.java
/****************** Exercise 30 *****************
 * Repair the program CADState.java as described in
 * the text.
 ***********************************************/
package io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract class Shape1 implements Serializable {
    public static final int RED = 1, BLUE = 2, GREEN = 3;
    private int xPos, yPos, dimension;
    private static Random rand = new Random(47);
    private static int counter = 0;

    public abstract void setColor(int newColor);

    public abstract int getColor();

    public Shape1(int xVal, int yVal, int dim) {
        xPos = xVal;
        yPos = yVal;
        dimension = dim;
    }

    public String toString() {
        return getClass() +
                "color[" + getColor() + "] xPos[" + xPos +
                "] yPos[" + yPos + "] dim[" + dimension + "]\n";
    }

    public static Shape randomFactory() {
        int xVal = rand.nextInt(100);
        int yVal = rand.nextInt(100);
        int dim = rand.nextInt(100);
        switch (counter++ % 3) {
            default:
            case 0:
                return new Circle(xVal, yVal, dim);
            case 1:
                return new Square(xVal, yVal, dim);
            case 2:
                return new Line(xVal, yVal, dim);
        }
    }
}

class Circle1 extends Shape {
    private static int color;

    public static void
    serializeStaticState(ObjectOutputStream os)
            throws IOException {
        os.writeInt(color);
    }

    public static void
    deserializeStaticState(ObjectInputStream os)
            throws IOException {
        color = os.readInt();
    }

    public Circle1(int xVal, int yVal, int dim) {
        super(xVal, yVal, dim);
    }

    public void setColor(int newColor) {
        color = newColor;
    }

    public int getColor() {
        return color;
    }
}

class Square1 extends Shape {
    private static int color;

    public static void
    serializeStaticState(ObjectOutputStream os)
            throws IOException {
        os.writeInt(color);
    }

    public static void
    deserializeStaticState(ObjectInputStream os)
            throws IOException {
        color = os.readInt();
    }

    public Square1(int xVal, int yVal, int dim) {
        super(xVal, yVal, dim);
    }

    public void setColor(int newColor) {
        color = newColor;
    }

    public int getColor() {
        return color;
    }
}

class Line1 extends Shape {
    private static int color;

    public static void
    serializeStaticState(ObjectOutputStream os)
            throws IOException {
        os.writeInt(color);
    }

    public static void
    deserializeStaticState(ObjectInputStream os)
            throws IOException {
        color = os.readInt();
    }

    public Line1(int xVal, int yVal, int dim) {
        super(xVal, yVal, dim);
    }

    public void setColor(int newColor) {
        color = newColor;
    }

    public int getColor() {
        return color;
    }

}

public class E30_RepairCADState {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        List<Shape> shapes = new ArrayList<Shape>();
        // Make some shapes:
        for (int i = 0; i < 10; i++)
            shapes.add(Shape.randomFactory());
        // Set all the static colors to GREEN:
        for (int i = 0; i < 10; i++)
            ((Shape) shapes.get(i)).setColor(Shape.GREEN);
        // Save the state vector:
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("CADState.out"));
        Circle1.serializeStaticState(out);
        Square1.serializeStaticState(out);
        Line.serializeStaticState(out);
        out.writeObject(shapes);
        // Display the shapes:
        System.out.println(shapes);
        // Now read the file back in:
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("CADState.out"));
        // Read in the same order they were written:
        Circle1.deserializeStaticState(in);
        Square1.deserializeStaticState(in);
        Line.deserializeStaticState(in);
        shapes = (List<Shape>) in.readObject();
        System.out.println(shapes);
    }
}