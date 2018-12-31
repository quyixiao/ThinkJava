package innerclasses;


interface Monster {
    void menace();
}

interface DangerousMonster extends Monster {
    void destroy();
}


/****
 * 199页
 * 创建一个类，它有非默认的构造器，即需要参数的构造器，创建第二个类，它包含一个方法，能够返回对
 * 第一个类的对象的引用，通过定一个继承自第一个类的匿名内部类，来创建一个返回对象
 *
 *
 * 1
 */
public class E14_HorrorShow2 {
    public static void main(String[] args) {
        DangerousMonster barney = new DangerousMonster() {
            public void menace() {
            }

            public void destroy() {
            }
        };
       /* HorrorShow.u(barney);
        HorrorShow.v(barney);*/
        /*Vampire vlad = new Vampire() {
            public void menace() {
            }

            public void destroy() {
            }

            public void kill() {
            }

            public void drinkBlood() {
            }
        };
        HorrorShow.u(vlad);
        HorrorShow.v(vlad);
        HorrorShow.w(vlad);*/
    }
}