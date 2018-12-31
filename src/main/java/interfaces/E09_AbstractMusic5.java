package interfaces;

import polymorphism.music.Note;

import static net.mindview.util.Print.print;

//: interfaces/E09_AbstractMusic5.java
/****************** Exercise 9 ******************
 * Refactor Music5.java by moving the common
 * methods in Wind, Percussion and Stringed into
 * an abstract class.
 ***********************************************/


/****
 *
 * 174页
 *
 * 重构Music5.java，将在Wind。 percussion和Strigned中的公共方法移入一个抽象类中
 *
 *
 * 1
 *
 */
abstract class Instrument {
    public void play(Note n) {
        print(this + ".play() " + n);
    }

    public void adjust() {
        print(this + ".adjust()");
    }

    // Forces implementation in derived class:
    public abstract String toString();
}

class Wind extends Instrument {
    public String toString() {
        return "Wind";
    }
}

class Percussion extends Instrument {
    public String toString() {
        return "Percussion";
    }
}

class Stringed extends Instrument {
    public String toString() {
        return "Stringed";
    }
}

class Brass extends Wind {
    public String toString() {
        return "Brass";
    }
}

class Woodwind extends Wind {
    public String toString() {
        return "Woodwind";
    }
}

public class E09_AbstractMusic5 {
    static void tune(Instrument i) {
        i.adjust();
        i.play(Note.MIDDLE_C);
    }

    static void tuneAll(Instrument[] e) {
        for (Instrument i : e)
            tune(i);
    }

    public static void main(String[] args) {
        Instrument[] orchestra = {
                new Wind(),
                new Percussion(),
                new Stringed(),
                new Brass(),
                new Woodwind()
        };
        tuneAll(orchestra);
    }
}