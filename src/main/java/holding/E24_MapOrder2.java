package holding;

import net.mindview.util.Countries;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;


/******
 *
 *
 * 236页
 *  使用String 键和你选择的对象填充LinkedHashMap，然后从中提取键值对，以键排序
 *  然后重新插入此Map
 */
public class E24_MapOrder2 {
    public static void main(String[] args) {
        Map<String, String> m1 =
                new LinkedHashMap<String, String>(
                        Countries.capitals(25));
        System.out.println(m1);
        String[] keys = m1.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        Map<String, String> m2 =
                new LinkedHashMap<String, String>();
        for (String key : keys)
            m2.put(key, m1.get(key));
        System.out.println(m2);
    }
}