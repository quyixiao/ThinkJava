package innerclasses;//: innerclasses/Parcel6.java
// Nesting a class within a scope.


import javax.sound.midi.Soundbank;

/****
 *      trackingSlip 类被嵌入在if语句的作用域内，这并不是说该类的创建是有条件的，它其实与另的类一起编写
 * 过的，然而，在定义trackingslip的作用域之外，它是不可用的，除此之外，它和普通类是一样的
 *
 */
public class Parcel6 {
    private void internalTracking(boolean b) {
        if (b) {
            class TrackingSlip {
                private String id;

                TrackingSlip(String s) {
                    id = s;
                }

                String getSlip() {
                    System.out.println("==========");
                    return id;
                }
            }
            TrackingSlip ts = new TrackingSlip("slip");
            String s = ts.getSlip();
        }
        // Can't use it here! Out of scope:
        //! TrackingSlip ts = new TrackingSlip("x");
    }

    public void track() {
        internalTracking(true);
    }

    public static void main(String[] args) {
        Parcel6 p = new Parcel6();
        p.track();
    }
} ///:~
