//: holding/E16_Vowels.java
/****************** Exercise 16 *****************
 * Create a Set of the vowels. Working from
 * UniqueWords.java, count and display the number of
 * vowels in each input word, and also display the
 * total number of vowels in the input file.
 ***********************************************/
package holding;

import net.mindview.util.TextFile;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static net.mindview.util.Print.print;


/******
 *
 *
 * 233页
 *      创建一个元商量过字母Set,对UniqueWords.java操作，计数并显示在每一个输入单词中
 *  的元音字母数量，并显示输入文件中的所有的元音字母的数量总和。
 *
 */
public class E16_Vowels {
    private final static Set<Character> vowels =
            new HashSet<Character>(Arrays.asList('a', 'e', 'o', 'u',
                    'i', 'A', 'E', 'O', 'U', 'I'));

    public static void main(String[] args) {
        HashSet<String> processedWords = new HashSet<String>();
        int fileVowels = 0;
        int wordVowels;
        for (String word :
                new TextFile("E16_Vowels.java", "\\W+")) {
            wordVowels = 0;
            for (char letter : word.toCharArray())
                if (vowels.contains(letter))
                    wordVowels++;
            if (!processedWords.contains(word)) {
                processedWords.add(word);
                print(word + " has " + wordVowels + " vowel(s)");
            }
            fileVowels += wordVowels;
        }
        print("Total number of vowels in file: " + fileVowels);
    }
}