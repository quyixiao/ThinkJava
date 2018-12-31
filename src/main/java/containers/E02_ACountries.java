//: containers/E02_ACountries.java
/****************** Exercise 2 ******************
 * Produce a Map and a Set containing all the
 * countries that begin with 'A'.
 ************************************************/
package containers;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static net.mindview.util.Countries.capitals;
import static net.mindview.util.Countries.names;


/****
 *
 *
 * 470页
 *
 *
 *
 *
 *
 *  1
 *
 *
 */
public class E02_ACountries {
    public static void main(String[] args) {
        TreeMap<String, String> map = new TreeMap<String, String>(capitals());
        TreeSet<String> set = new TreeSet<String>(names());
        String b = null;
        for (String s : map.keySet()) {
            System.out.println(s);
            if (s.startsWith("B")) {
                b = s;
                break;
            }
        }
        Map<String, String> aMap = map.headMap(b);
        System.out.println(b);
        Set<String> aSet = set.headSet(b);
        System.out.println("aMap = " + aMap);
        System.out.println("aSet = " + aSet);
    }
}