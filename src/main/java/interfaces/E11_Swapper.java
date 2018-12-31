//: interfaces/E11_Swapper.java
/****************** Exercise 11 ******************
 * Create a class with a method that takes a String
 136 Thinking in Java, 4th Edition Annotated Solution Guide
 * argument and produces a result that swaps each
 * pair of characters in that argument. Adapt the
 * class to work with
 * interfaceprocessor.Apply.process().
 ***********************************************/
package interfaces;

import interfaces.interfaceprocessor.Apply;
import interfaces.interfaceprocessor.Processor;


/*****
 * 178页
 * 创建一个类，它有一个方法用于的接受一个String类型的参数，生成结果是将该参数中的每一个
 * 参数每一对字符进行互换，对该类进行适配，使得它可以用于Interfaceprocessor.Apply.process()方法
 *
 */

class CharacterPairSwapper {
    static String swap(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length() - 1; i += 2) {
            char c1 = sb.charAt(i);
            char c2 = sb.charAt(i + 1);
            sb.setCharAt(i, c2);
            sb.setCharAt(i + 1, c1);
        }
        return sb.toString();
    }
}

class SwapperAdapter implements Processor {
    public String name() {
        return CharacterPairSwapper.class.getSimpleName();
    }

    public String process(Object input) {
        return CharacterPairSwapper.swap((String) input);
    }
}

public class E11_Swapper {
    public static void main(String[] args) {
        Apply.process(new SwapperAdapter(), "1234");
        Apply.process(new SwapperAdapter(), "abcde");
    }
}