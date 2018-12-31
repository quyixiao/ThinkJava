package innerclasses;//: innerclasses/Parcel11.java
// Nested classes (static inner classes).


import generics.coffee.Latte;

/****
 *  201页
 *  要创建嵌套类的对象，并不需要其外围类的对象
 *  不能从嵌套类的对象中访问非静态的外围类对象
 *
 *
 *  嵌套类与普通的内部类还有一个区别，普通内部类的字段与方法，只能放在类的
 *  外部层次上，所以数码通的内部类不能有static数据和static字段，也不能包含
 *  嵌套类，但是嵌套类可以包含所有的这些东西
 *
 *
 *
 *  就像你在本章前面看到的那样，在一个普通的非static内部类中，通过一个特殊的this
 *  引用可以链接到外围炎龙骑士旬，嵌套类就没有这个特殊的this引用，这使得它类似于
 *  一个static方法
 *
 *
 *
 */
public class Parcel11 {
    private static class ParcelContents implements Contents {
        private int i = 11;

        public int value() {
            return i;
        }
    }
    protected static class ParcelDestination implements Destination {
        private String label;

        private ParcelDestination(String whereTo) {
            label = whereTo;
            System.out.println(this.label);
        }

        public String readLabel() {

            return label;
        }

        // Nested classes can contain other static elements:
        public static void f() {
            System.out.println("1111111");
        }

        static int x = 10;

        static class AnotherLevel {
            public static void f() {
                System.out.println("222222222");
            }

            static int x = 11;
        }
    }

    public static Destination destination(String s) {
        return new ParcelDestination(s);
    }

    public static Contents contents() {
        return new ParcelContents();
    }

    public static void main(String[] args) throws Exception{
        Contents c = contents();
        Destination d = destination("Tasmania");
        d.readLabel();
        ParcelDestination f =(ParcelDestination) destination("Tasmania");


        System.out.println(f.label);
        System.out.println(f.readLabel());
        f.f();
        System.out.println(f.x);
        System.out.println(Parcel11.ParcelDestination.AnotherLevel.x);

        Parcel11.ParcelDestination.AnotherLevel.f();
    }
}



///:~
