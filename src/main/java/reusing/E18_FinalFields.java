package reusing;


//: reusing/E18_FinalFields.java

/****************** Exercise 18 *****************
 * Create a class with a static final field and a
 * final field and demonstrate the difference
 * between the two.
 ***********************************************/

import javax.sound.midi.Soundbank;

/****
 * 142页
 *
 * 创建一个含有static final域和final域的类，说明二者间的区别
 *
 */
class SelfCounter {
    private static int count;
    private int id = count++;

    public String toString() {

        return "SelfCounter " + id + "  count="+count;
    }
}

class WithFinalFields {
    final SelfCounter scf = new SelfCounter();
    static final SelfCounter scsf = new SelfCounter();

    public String toString() {
        return "scf = " + scf + "\nscsf = " + scsf;
    }
}


public class E18_FinalFields {
    public static void main(String args[]) {
        System.out.println("First object:");
        System.out.println(new WithFinalFields());
        System.out.println("Second object:");
        System.out.println(new WithFinalFields());
    }
}