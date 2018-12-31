package polymorphism;


//: polymorphism/E12_RodentInitialization.java
/****************** Exercise 12 *****************
 * Modify Exercise 9 so it demonstrates the
 * order of initialization of the base classes
 * and derived classes. Now add member objects to
 * both the base and derived classes, and show
 * the order in which their initialization occurs
 * during construction.
 ***********************************************/
import static net.mindview.util.Print.*;


/******
 *
 *
 * 161页
 *  修改练习9，使其能够演示基类和展示出类的初始化顺序，然后向基类和导出类
 *  中添加对象，并说明构建初始化发生的有顺序
 *
 *
 */

class Member {
    public Member(String id) {
        print("Member constructor " + id);
    }
}

class Rodent2 {
    Member m1 = new Member("r1"), m2 = new Member("r2");

    public Rodent2() {
        print("Rodent constructor");
    }

    public void hop() {
        print("Rodent hopping");
    }

    public void scurry() {
        print("Rodent scurrying");
    }

    public void reproduce() {
        print("Making more Rodents");
    }

    public String toString() {
        return "Rodent";
    }
}

class Mouse2 extends Rodent2 {
    Member m1 = new Member("m1"), m2 = new Member("m2");

    public Mouse2() {
        print("Mouse constructor");
    }

    public void hop() {
        print("Mouse hopping");
    }


    public void scurry() {
        print("Mouse scurrying");
    }

    public void reproduce() {
        print("Making more Mice");
    }

    public String toString() {
        return "Mouse";
    }
}

class Gerbil2 extends Mouse2 {
    Member m1 = new Member("g1"), m2 = new Member("g2");

    public Gerbil2() {
        print("Gerbil constructor");
    }

    public void hop() {
        print("Gerbil hopping");
    }

    public void scurry() {
        print("Gerbil scurrying");
    }

    public void reproduce() {
        print("Making more Gerbils");
    }

    public String toString() {
        return "Gerbil";
    }
}

class Hamster2 extends Gerbil2 {
    Member m1 = new Member("h1"), m2 = new Member("h2");

    public Hamster2() {
        print("Hamster constructor");
    }

    public void hop() {
        print("Hamster hopping");
    }

    public void scurry() {
        print("Hamster scurrying");
    }

    public void reproduce() {
        print("Making more Hamsters");
    }

    public String toString() {
        return "Hamster";
    }
}

public class E12_RodentInitialization {
    public static void main(String args[]) {
        new Hamster2();
    }
}