package generics;//: generics/GenericReading.java

import java.util.Arrays;
import java.util.List;


/****
 *
 *
 * 394
 *
 *
 *
 * 1
 *
 *
 *
 * 某种具体的类型，这样就可以安全的将一个T类型的对象或者从T导出的任何对象作为参数传递给List的方法，在f2（）中可以看到这一点，这个方法中我
 * 们仍旧可以像前面的那样，将Apple放置到List<Apple></Apple>中，但是现在我们可以如何所期望的那样，将Apple放置到List<Fruit>中
 *
 *
 *
 */
public class GenericReading {
    static <T> T readExact(List<T> list) {
        return list.get(0);
    }

    static List<Apple> apples = Arrays.asList(new Apple());

    static List<Fruit> fruit = Arrays.asList(new Fruit());

    // A static method adapts to each call:
    static void f1() {
        Apple a = readExact(apples);
        Fruit f = readExact(fruit);
        f = readExact(apples);
    }

    // If, however, you have a class, then its type is
    // established when the class is instantiated:
    static class Reader<T> {
        T readExact(List<T> list) {
            return list.get(0);
        }
    }

    static void f2() {
        Reader<Fruit> fruitReader = new Reader<Fruit>();
        Fruit f = fruitReader.readExact(fruit);
        // Fruit a = fruitReader.readExact(apples); // Error:
        // readExact(List<Fruit>) cannot be
        // applied to (List<Apple>).
    }

    static class CovariantReader<T> {
        T readCovariant(List<? extends T> list) {
            return list.get(0);
        }
    }

    static void f3() {
        CovariantReader<Fruit> fruitReader = new CovariantReader<Fruit>();
        Fruit f = fruitReader.readCovariant(fruit);
        Fruit a = fruitReader.readCovariant(apples);
    }

    public static void main(String[] args) {
        f1();
        f2();
        f3();
    }
} ///:~
