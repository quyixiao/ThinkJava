//: reusing/E16_Frog.java
/****************** Exercise 16 *****************
 * Create a class called Amphibian. From it,
 * inherit a class from it called Frog. Put
 * appropriate methods in the base class. In
 * main(), create a Frog, upcast it to Amphibian,
 * and demonstrate that all the methods still work.
 ***********************************************/
package reusing;


/****
 * 140页
 * 创建一个名为Amphibian的类，由此继承产生一个称为Frog的类，在基类中设置
 * 适当的方法，在main()中，创建一个Frag并身上转型至Amphibian，然后说明所有方法都可工作
 *
 *
 *
 * 1
 *
 */
class Amphibian {
    public void moveInWater() {
        System.out.println(this.getClass().getSimpleName() + " Moving in Water");
    }

    public void moveOnLand() {
        System.out.println(this.getClass().getSimpleName() + " Moving on Land");
    }
}

class Frog extends Amphibian {

}

public class E16_Frog {
    public static void main(String args[]) {
        Amphibian a = new Frog();
        a.moveInWater();
        a.moveOnLand();
    }
}