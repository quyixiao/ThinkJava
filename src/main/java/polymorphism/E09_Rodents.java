package polymorphism;


import static net.mindview.util.Print.print;


/*****
 *
 *
 * 153页
 *  创建Rodent龋齿动物，Mouse 老鼠，Cerbil鼹鼠,Hanmster，等等这样一个的继承层次结构
 *  ，在基类中，提供对所有Rodent都通用方法，在导出类中，根据特定的Rodent类型，然后调用基类
 *  方法，以便它们执行不同的行为，创建一个Robent数组，填充不同的Rodent类，然后调用基类方法
 *  ，观察自己处理什么情况
 *
 */

class Rodent {
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

class Mouse extends Rodent {
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

class Gerbil extends Rodent {
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

class Hamster extends Rodent {
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


public class E09_Rodents {
    public static void main(String args[]) {
        Rodent[] rodents = {
                new Mouse(),
                new Gerbil(),
                new Hamster(),
        };
        for (Rodent r : rodents) {
            r.hop();
            r.scurry();
            r.reproduce();
            print(r);
        }
    }
}