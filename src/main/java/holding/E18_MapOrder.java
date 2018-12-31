//: holding/E18_MapOrder.java
/******************* Exercise 18 ************************
 * Fill a HashMap with key-value pairs. Print the results
 * to show ordering by hash code. Extract the pairs, sort
 * by key, and place the result into a LinkedHashMap.
 * Show that insertion order is maintained.
 *******************************************************/
package holding;

import net.mindview.util.Countries;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/*****
 *
 * 235页
 * 用键值填充一个HashMap，打印出结果，通过散列码来展示其排序，抽取这些
 * 键值对进行排序，并将结果回
 *
 *
 * 1
 */
public class E18_MapOrder {
    public static void main(String[] args) {
        Map<String, String> m1 = new HashMap<String, String>(Countries.capitals(25));
        System.out.println(m1);
        String[] keys = m1.keySet().toArray(new String[0]);
        System.out.println("=======================");
        System.out.println(Arrays.toString(keys));
        Arrays.sort(keys);

        Map<String, String> m2 = new LinkedHashMap<String, String>();
        for (String key : keys) {
            m2.put(key, m1.get(key));
        }
        System.out.println(m2);
    }
}