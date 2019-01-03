//: containers/E19_WordCounter3.java
/****************** Exercise 19 *****************
 * Repeat Exercise 13 using a SimpleHashMap.
 ***********************************************/
package containers;

import net.mindview.util.TextFile;

import java.util.List;


/***
 * 495页
 *
 *
 *
 * 1
 */
public class E19_WordCounter3 {
    public static void main(String[] args) {
        List<String> words = new TextFile("/Users/quyixiao/Desktop/source/ThinkJava/src/main/java/containers/E12_MapsDemo.java", "\\W+");
        SimpleHashMap<String, Integer> map = new SimpleHashMap<String, Integer>();
        for (String word : words) {
            Integer freq = map.get(word);
            map.put(word, freq == null ? 1 : freq + 1);
        }
        System.out.println(map);
    }
}