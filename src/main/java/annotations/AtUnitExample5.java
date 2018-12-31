//: annotations/AtUnitExample5.java
package annotations;

import net.atunit.Test;
import net.atunit.TestObjectCleanup;
import net.atunit.TestObjectCreate;
import net.atunit.TestProperty;
import net.mindview.util.OSExecute;

import java.io.IOException;
import java.io.PrintWriter;


/****
 *
 * 639页
 * @TestProperty 也可以用来标记那些只在测试中使用的方法，而他们本身又不是测试方法。
 * 注意，这个程序依赖于测试执行的顺序，这可不是一个好的实践。
 *如果你的测试对象需要执行某些初始化的工作，并且使用完毕后，该方法会为你执行，清理工作，
 * 在下面的例子中，@testObjectCreate为每个测试对象打开了一个文件，因此必须在丢弃测试对象的时候关闭该文件。
 *
 *
 * 从输出中我们可以看出，清理方法会在每个测试结束后自动运行。
 *
 * 这种方法潜在的唯一缺点是：继承使我们失去了被测试的类中private方法的能力，如果这对你很重要，那么将
 * private方法改为protected，要么添加一个非private的TestProperty方法，由它来调用private方法（稍后我们
 * 就可以看到，AtUnitRemover工具会将@testPropery方法从产品的代码中自动删除掉）
 *
 * 与Jnuiit相比，@Unit有一个比较在的优点，就是@Unit不需要套件，在Junit中，要目标就是使@Unit测试系统尽可能的
 * 透明，使得程序员在用它的时候只需添加@Test方法，而不需要像Jnuit等其他单元测试框架所要求的那些特殊的编码或者
 * 知识，不过，如果编写测试不会遇到任何障碍，这也不太可能，因此@Unit会尽量让这些困难变得微不足道。希望通过这种方式。
 * 程序员会更乐意编写测试。
 *
 *
 *
 * 实现@Unit
 * 首先，我们需要定义所有的注解类型，这些都是简单的标签，并且没有属性，@Test标签在本章开头己经定义过了，这里其他所需的注解。
 *

 *
 */
public class AtUnitExample5 {
    private String text;

    public AtUnitExample5(String text) {
        this.text = text;
    }

    public String toString() {
        return text;
    }

    @TestProperty
    static PrintWriter output;
    @TestProperty
    static int counter;

    @TestObjectCreate
    static AtUnitExample5 create() {
        String id = Integer.toString(counter++);
        try {
            output = new PrintWriter("Test" + id + ".txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new AtUnitExample5(id);
    }

    @TestObjectCleanup
    static void
    cleanup(AtUnitExample5 tobj) {
        System.out.println("Running cleanup");
        output.close();
    }

    @Test
    boolean test1() {
        output.print("test1");
        return true;
    }

    @Test
    boolean test2() {
        output.print("test2");
        return true;
    }

    @Test
    boolean test3() {
        output.print("test3");
        return true;
    }

    public static void main(String[] args) throws Exception {
        OSExecute.command(
                "java net.mindview.atunit.AtUnit AtUnitExample5");
    }
} /* Output:
annotations.AtUnitExample5
  . test1
Running cleanup
  . test2
Running cleanup
  . test3
Running cleanup
OK (3 tests)
*///:~
