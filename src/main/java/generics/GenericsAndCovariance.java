package generics;//: generics/GenericsAndCovariance.java

import java.util.ArrayList;
import java.util.List;


/****
 *
 *
 *
 * 391
 *
 * 
 *
 *
 * 1
 *
 *
 *
 */
public class GenericsAndCovariance {
    public static void main(String[] args) {
        // Wildcards allow covariance:
        List<? extends Fruit> flist = new ArrayList<Apple>();
        // Compile Error: can't add any type of object:
        //flist.add(new Apple());
        //flist.add(new Fruit());
        // flist.add(new Object());
        flist.add(null); // Legal but uninteresting
        // We know that it returns at least Fruit:
        Fruit f = flist.get(0);
    }
} ///:~
