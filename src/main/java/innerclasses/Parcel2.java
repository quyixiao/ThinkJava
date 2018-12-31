package innerclasses;//: innerclasses/Parcel2.java
// Returning a reference to an inner class.


/****
 * 如果想从外部类的非静态方法之外的任意位置创建某个内部类的对象，那么必须在
 * main()方法中那样，具体地指明这个对象的类型，OuterClassName.InnerClassName
 * 
 */
public class Parcel2 {
    class Contents {
        private int i = 11;

        public int value() {
            return i;
        }
    }

    class Destination {
        private String label;
        Destination(String whereTo) {
            label = whereTo;
        }

        String readLabel() {
            return label;
        }
    }

    public Destination to(String s) {
        return new Destination(s);
    }

    public Contents contents() {
        return new Contents();
    }

    public void ship(String dest) {
        Contents c = contents();
        Destination d = to(dest);
        System.out.println("===========" + d.readLabel());
    }

    public static void main(String[] args) {
        Parcel2 p = new Parcel2();
        p.ship("Tasmania");
        Parcel2 q = new Parcel2();
        // Defining references to inner classes:
        Parcel2.Contents c = q.contents();
        Parcel2.Destination d = q.to("Borneo");
        System.out.println(d.readLabel());
    }
} /* Output:
Tasmania
*///:~
