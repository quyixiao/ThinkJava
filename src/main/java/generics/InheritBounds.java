package generics;//: generics/InheritBounds.java


/****
 *
 * 388
 *
 *
 * HoldItem直接持有一个对象，因此这种行为被继承到了Colored2中，它也要求其
 * 参数与HasColor一致，ColoredDimension2和solid进一步扩展到这个层次结构，并在每个层次上都添加了
 * 边界，现在这些方法被继承，因而不必在每个类中重复
 *
 *
 *
 *
 * 1
 * @param <T>
 */
class HoldItem<T> {
    T item;

    HoldItem(T item) {
        this.item = item;
    }

    T getItem() {
        return item;
    }
}

class Colored2<T extends HasColor> extends HoldItem<T> {
    Colored2(T item) {
        super(item);
    }

    java.awt.Color color() {
        return item.getColor();
    }
}

class ColoredDimension2<T extends Dimension & HasColor> extends Colored2<T> {
    ColoredDimension2(T item) {
        super(item);
    }

    int getX() {
        return item.x;
    }

    int getY() {
        return item.y;
    }

    int getZ() {
        return item.z;
    }
}

class Solid2<T extends Dimension & HasColor & Weight>
        extends ColoredDimension2<T> {
    Solid2(T item) {
        super(item);
    }

    int weight() {
        return item.weight();
    }
}


public class InheritBounds {
    public static void main(String[] args) {
        Solid2<Bounded> solid2 = new Solid2<Bounded>(new Bounded());
        solid2.color();
        solid2.getY();
        solid2.weight();
    }
} ///:~
