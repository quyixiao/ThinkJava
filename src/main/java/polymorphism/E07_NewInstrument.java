//: polymorphism/E07_NewInstrument.java
/****************** Exercise 7 *****************
 * Add a new type of Instrument to Music3.java
 * and verify that polymorphism works for your
 * new type.
 ***********************************************/
package polymorphism;

import polymorphism.music.Note;

import static net.mindview.util.Print.print;


/****
 * 153页
 *
 *  向Music3.java添加一个新的类型Instrument,并验证多态性是否作用于所添加的新类型
 *
 *
 * 1
 */
class Electronic extends Instrument {
    void play(Note n) {
        print("Electronic.play() " + n);
    }

    public String toString() {
        return "Electronic";
    }
}

public class E07_NewInstrument {
    static Instrument[] orchestra = {
            new Wind(),
            new Percussion(),
            new Stringed(),
            new Brass(),
            new Woodwind(),
            new Electronic()
    };

    public static void main(String args[]) {
        for (Instrument i : orchestra) {
            i.play(Note.MIDDLE_C);
            i.adjust();
            print(i);
        }
    }
}