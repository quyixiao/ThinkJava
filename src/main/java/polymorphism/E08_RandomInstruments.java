//: polymorphism/E08_RandomInstruments.java
/****************** Exercise 8 *****************
 * Modify Music3.java so it randomly creates
 * Instrument objects the way Shapes.java does.
 ***********************************************/
package polymorphism;


/****
 * 153页
 * 修改Music3.java，使其可以像Shapes.java中方式那样随机创建
 * Instrument对象
 *
 */
class InstrumentGenerator {
    java.util.Random gen = new java.util.Random(47);

    public Instrument next() {
        switch (gen.nextInt(6)) {
            default:
            case 0:
                return new Wind();
            case 1:
                return new Percussion();
            case 2:
                return new Stringed();
            case 3:
                return new Brass();
            case 4:
                return new Woodwind();
            case 5:
                return new Electronic();
        }
    }
}

public class E08_RandomInstruments {
    public static void main(String args[]) {
        InstrumentGenerator gen = new InstrumentGenerator();
        for (int i = 0; i < 20; i++)
            System.out.println(gen.next());
    }
}