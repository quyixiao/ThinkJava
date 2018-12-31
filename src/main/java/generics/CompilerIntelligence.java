package generics;//: generics/CompilerIntelligence.java

import java.util.Arrays;
import java.util.List;

public class CompilerIntelligence {
    public static void main(String[] args) {
        List<? extends Fruit> flist = Arrays.asList(new Apple());
        Apple a = (Apple) flist.get(0); // No warning
        boolean b = flist.contains(new Apple()); // Argument is 'Object'
        System.out.println(b);

        int i = flist.indexOf(new Apple()); // Argument is 'Object'
        System.out.println(i);
    }
} ///:~
