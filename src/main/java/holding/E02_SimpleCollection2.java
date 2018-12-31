//: holding/E02_SimpleCollection2.java
/****************** Exercise 2 ******************
 * Modify SimpleCollection.java to use a Set for c.
 ***********************************************/
package holding;

import java.util.Collection;
import java.util.HashSet;


/******
 *
 *
 * 220页
 *  修改SimpleConllection.java使用Set来表示c
 *
 */
public class E02_SimpleCollection2 {
    public static void main(String[] args) {
        Collection<Integer> c = new HashSet<Integer>();
        for (int i = 0; i < 10; i++)
            c.add(i); // Autoboxing
        for (Integer i : c)
            System.out.print(i + ", ");
    }
}