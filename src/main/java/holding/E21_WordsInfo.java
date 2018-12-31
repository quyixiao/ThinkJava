package holding;

import net.mindview.util.TextFile;

import java.util.*;


/******
 *
 *  236页
 * 通过使用Map<String.interge>遵循UniqueWords.java的形式来创建一个程序，它可以对一个文件中的出现
 * 的单词计数，使用带有第二个参数String.CASE_INSSENSITIVE_ORDE的Conllections.sort()方法对结果
 * 进行排序，将产生的字母序，然后显示结果
 *
 */
public class E21_WordsInfo {
    public static void main(String[] args) {
        Map<String, Integer> wordsStat =
                new HashMap<String, Integer>();
        for (String word :
                new TextFile("E21_WordsInfo.java", "\\W+")) {
            Integer freq = wordsStat.get(word);
            wordsStat.put(word, freq == null ? 1 : freq + 1);
        }
        List<String> keys =
                new ArrayList<String>(wordsStat.keySet());
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
        for (String key : keys)
            System.out.println(key + " => " + wordsStat.get(key));
    }
}