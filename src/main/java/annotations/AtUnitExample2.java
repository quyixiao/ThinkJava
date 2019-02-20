//: annotations/AtUnitExample2.java
// Assertions and exceptions can be used in @Tests.
package annotations;

import net.mindview.atunit.Test;
import net.mindview.util.OSExecute;

import java.io.FileInputStream;
import java.io.IOException;


/****
 * 第二种形式，在这种情况下，要表示测试成功，可以使用java的assert语句Java的断言机制一般要求程序员在
 * java命令行中加上ea标志，不过@unit已经自动的打开了该功能，而要表示测试失败的话，你甚至可以使用异常，
 * @unit的设计目标之一就是尽可能少添加额外的语法，而java的assert和异常对于报告猿题库高中而言，已经足够了
 * 一个失败的assert或从测试方法中抛出异常，都将被看作一个失败的测试，但是@Unit并不会就在这个失败的测试上打住
 * 它会继续运行，直到所有的测试都运行完毕，下面就是一个示例程序
 *
 *
 * 1
 *
 *
 */
public class AtUnitExample2 {
    public String methodOne() {
        return "This is methodOne";
    }

    public int methodTwo() {
        System.out.println("This is methodTwo");
        return 2;
    }

    @Test
    void assertExample() {
        assert methodOne().equals("This is methodOne");
    }

    @Test
    void assertFailureExample() {
        assert 1 == 2 : "What a surprise!";
    }

    @Test
    void exceptionExample() throws IOException {
        new FileInputStream("nofile.txt"); // Throws
    }

    @Test
    boolean assertAndReturn() {
        // Assertion with message:
        assert methodTwo() == 2 : "methodTwo must equal 2";
        return methodOne().equals("This is methodOne");
    }

    public static void main(String[] args) throws Exception {
        OSExecute.command(
                "java net.mindview.atunit.AtUnit AtUnitExample2");
    }
} /* Output:
annotations.AtUnitExample2
  . assertExample
  . assertFailureExample java.lang.AssertionError: What a surprise!
(failed)
  . exceptionExample java.io.FileNotFoundException: nofile.txt (The system cannot find the file specified)
(failed)
  . assertAndReturn This is methodTwo

(4 tests)

>>> 2 FAILURES <<<
  annotations.AtUnitExample2: assertFailureExample
  annotations.AtUnitExample2: exceptionExample
*///:~
