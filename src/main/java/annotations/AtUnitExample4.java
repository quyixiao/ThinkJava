//: annotations/AtUnitExample4.java
package annotations;

import net.atunit.Test;
import net.atunit.TestObjectCreate;
import net.atunit.TestProperty;
import net.mindview.util.OSExecute;

import java.util.*;

import static net.mindview.util.Print.print;


/*****
 *
 * 639页
 *
 * 加入的@testObjectCreate注解的方法声明为static,且必须返回一个你正在测试类型的对象，这一切都由@Unit负责确保成立。
 *
 * 有的时候，我们要向单元测试中添加额外的域，这时可以使用@TestProperty注解，由它注解的域表示只在单元测试中使用（因此
 * ，在我们将产品发布之前，他们应该被删除掉）在下面的例子中，一个String通过String.split()方法被，从其中读取一个值，这
 * 值将被用来测试对象。
 *
 *
 *
 *
 *
 *
 *
 */

public class AtUnitExample4 {
    static String theory = "All brontosauruses " +
            "are thin at one end, much MUCH thicker in the " +
            "middle, and then thin again at the far end.";
    private String word;
    private Random rand = new Random(); // Time-based seed

    public AtUnitExample4(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public String scrambleWord() {
        List<Character> chars = new ArrayList<Character>();
        for (Character c : word.toCharArray())
            chars.add(c);
        Collections.shuffle(chars, rand);
        StringBuilder result = new StringBuilder();
        for (char ch : chars)
            result.append(ch);
        return result.toString();
    }

    @TestProperty
    static List<String> input =
            Arrays.asList(theory.split(" "));
    @TestProperty
    static Iterator<String> words = input.iterator();

    @TestObjectCreate
    static AtUnitExample4 create() {
        if (words.hasNext())
            return new AtUnitExample4(words.next());
        else
            return null;
    }

    @Test
    boolean words() {
        print("'" + getWord() + "'");
        return getWord().equals("are");
    }

    @Test
    boolean scramble1() {
        // Change to a specific seed to get verifiable results:
        rand = new Random(47);
        print("'" + getWord() + "'");
        String scrambled = scrambleWord();
        print(scrambled);
        return scrambled.equals("lAl");
    }

    @Test
    boolean scramble2() {
        rand = new Random(74);
        print("'" + getWord() + "'");
        String scrambled = scrambleWord();
        print(scrambled);
        return scrambled.equals("tsaeborornussu");
    }

    public static void main(String[] args) throws Exception {
        System.out.println("starting");
        OSExecute.command(
                "java net.mindview.atunit.AtUnit AtUnitExample4");
    }
} /* Output:
starting
annotations.AtUnitExample4
  . scramble1 'All'
lAl

  . scramble2 'brontosauruses'
tsaeborornussu

  . words 'are'

OK (3 tests)
*///:~
