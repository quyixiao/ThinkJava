package concurrency;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayTest {
    public static void main(String[] args) {


        ArrayList list = new ArrayList(Collections.nCopies(100, 0));

        list.set(1,1);
        System.out.println(list.size());


        System.out.println("index 1 = " + list.get(1));
        System.out.println(list.size());



    }
}




