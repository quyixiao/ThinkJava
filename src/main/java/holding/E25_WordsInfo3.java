package holding;

import net.mindview.util.TextFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/*******
 * 236页
 *      创建一个Map<String.ArrayList<Integer>> ，使用net.mindview.TextFile来打开
 *  一个文本文件，并一次读入一个单词，（使用 "\\W+" 作为TextFile构造器的第二个参数）在读这个单词时
 *  它们进行了计数，并且对于文件中的每一个单词，都在ArrayList<Integer>中记录下与这个词相关联的单词计数，实际上
 *  它记录的是单词在文件中被发现的位置
 *
 *
 */
public class E25_WordsInfo3 {
    public static void main(String[] args) {
        Map<String, ArrayList<Integer>> stat =
                new HashMap<String, ArrayList<Integer>>();
        int wordCount = 0;
        for (String word :
                new TextFile("E25_WordsInfo3.java", "\\W+")) {
            ArrayList<Integer> loc = stat.get(word);
            if (loc == null) {
                loc = new ArrayList<Integer>();
                stat.put(word, loc);
            }
            loc.add(++wordCount);
        }
        System.out.println(stat);
    }
}