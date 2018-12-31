package reusing;//: reusing/Wind.java
// Inheritance & upcasting.


/****
 * 139页
 *
 * 1
 *
 *
 * 二胡在演奏
 */
class Instrument {
    public void play() {
        System.out.println(this.getClass().getSimpleName()+".play()");
    }

    static void tune(Instrument i) {
        i.play();
    }
}


class Erhu extends Instrument{

}

// Wind objects are instruments
// because they have the same interface:
public class Wind extends Instrument {
    public static void main(String[] args) {
        Instrument.tune(new Wind()); // Upcasting
        Instrument.tune(new Erhu());
    }
} ///:~
