package interfaces;

import polymorphism.music.Note;

import static net.mindview.util.Print.print;

//: interfaces/E10_PlayableMusic5.java
/****************** Exercise 10 ******************
 * Add a Playable to Modify Music5.java, and move
 * the play() declaration from Instrument to
 * Playable. Include Playable in the implements
 * list to add it to the derived classes.
 * Change tune() so it takes a Playable instead
 * of an Instrument.
 ***********************************************/


/*****
 *
 * 174页
 * 修改Music5.java ，添加Playable接口，将Play()的声明从Instrument中移到Playable中，通过将Playable包括在
 * implements列表中，把Playable添加导出类中，修改tune()使他接受Playable而不是Instrument作为参数
 *
 *
 */
interface Instrument2 {
    void adjust();
}

interface Playable {
    void play(Note n);
}

class Wind2 implements Instrument2, Playable {
    public void play(Note n) {
        print(this + ".play() " + n);
    }

    public String toString() {
        return "Wind";
    }

    public void adjust() {
        print(this + ".adjust()");
    }
}

class Percussion2 implements Instrument2, Playable {
    public void play(Note n) {
        print(this + ".play() " + n);
    }

    public String toString() {
        return "Percussion";
    }

    public void adjust() {
        print(this + ".adjust()");
    }
}

class Stringed2 implements Instrument2, Playable {
    public void play(Note n) {
        print(this + ".play() " + n);


    }

    public String toString() {
        return "Stringed";
    }

    public void adjust() {
        print(this + ".adjust()");
    }
}

class Brass2 extends Wind2 {
    public String toString() {
        return "Brass";
    }
}

class Woodwind2 extends Wind2 {
    public String toString() {
        return "Woodwind";
    }
}

public class E10_PlayableMusic5 {
    static void tune(Playable p) {
        p.play(Note.MIDDLE_C);
    }

    static void tuneAll(Playable[] e) {
        for (Playable p : e)
            tune(p);
    }

    public static void main(String[] args) {
        Playable[] orchestra = {
                new Wind2(),
                new Percussion2(),
                new Stringed2(),
                new Brass2(),
                new Woodwind2()
        };
        tuneAll(orchestra);
    }
}