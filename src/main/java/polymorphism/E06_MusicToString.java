package polymorphism;

import polymorphism.music.Note;

import static net.mindview.util.Print.print;

//: polymorphism/E06_MusicToString.java
/****************** Exercise 6 *****************
 * Change Music3.java so what() becomes the root
 * Object method toString(). Print the Instrument
 * objects using System.out.println() (without
 * any casting).
 ***********************************************/

/******
 *
 * 155页
 *  修改music3.java。使what()方法为根Object的toString()方法，试用
 *  system.out.println()方法打印Instrument对象不用身上转型
 *
 *
 *1
 */

class Instrument {
    void play(Note n) {
        print("Instrument.play() " + n);
    }

    public String toString() {
        return "Instrument";
    }

    void adjust() {
    }
}


class Wind extends Instrument {
    void play(Note n) {
        print("Wind.play() " + n);
    }

    public String toString() {
        return "Wind";
    }
}

class Percussion extends Instrument {
    void play(Note n) {
        print("Percussion.play() " + n);
    }

    public String toString() {
        return "Percussion";
    }
}

class Stringed extends Instrument {
    void play(Note n) {
        print("Stringed.play() " + n);
    }

    public String toString() {
        return "Stringed";
    }
}

class Brass extends Wind {
    void play(Note n) {
        print("Brass.play() " + n);
    }

    void adjust() {
        print("Brass.adjust()");
    }
}

class Woodwind extends Wind {
    void play(Note n) {
        print("Woodwind.play() " + n);
    }

    public String toString() {
        return "Woodwind";
    }
}


public class E06_MusicToString {
    static Instrument[] orchestra = {
            new Wind(),
            new Percussion(),
            new Stringed(),
            new Brass(),
            new Woodwind()
    };

    public static void printAll(Instrument[] orch) {
        for (Instrument i : orch)
            System.out.println(i);
    }

    public static void main(String args[]) {
        printAll(orchestra);
    }
}