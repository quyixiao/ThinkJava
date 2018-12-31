//: annotations/AtUnitExternalTest.java
// Creating non-embedded tests.
package annotations;

import net.atunit.Test;
import net.mindview.util.OSExecute;


/***
 * 636页
 * 这个例子还表现出了灵活命名的价值（与Junit不同，它要求你必须使用test作为测试方法的前缀）。在这里，
 * @Test 方法被命名为下划线前缀加上这将要测试的方法的名字（我并不认为这是一个理想的命名形式，只是
 * 表现一种可能性罢了。
 *
 * 或者你还可以使用组合的方式创建非嵌入式的测试：
 * 因为每个测试对应一个新创建的AtUnitComposition对象，因此每个测试也对应一个新的成员testObject
 * @Unit中并没有Junit里的特殊的Assert方法，不过@test方法仍然允许程序员返回void(如果你还是想用true或false)的话
 * ，你仍然可以用boolean作为方法返回值类型），这是@test方法的第二种形式，在这种情况下，要表示测试成功，可以使用java的asset语句。
 * Java的断言机制一般要求程序员在java命令行中加上-ea标志，不过@Unit己经自动打开了该功能。而要表示测试失败的话，你甚至可以使用
 * 异常，@Uit的设计目标之一就是尽可能少地添加额外的语法，而java的asset和异常对于报告错误而言，已经足够了，一个失败的Asset 或从测试方法中。
 * 抛出异常，都将被看作一个失败的测试，但是@Unit并不会就在这个失败的测试上打住，它会继续运行，直到所有的测试都运行完毕，下面是一个示例程序。
 * 
 *
 */
public class AtUnitExternalTest extends AtUnitExample1 {
  @Test
  boolean _methodOne() {
    return methodOne().equals("This is methodOne");
  }
  @Test boolean _methodTwo() { return methodTwo() == 2; }
  public static void main(String[] args) throws Exception {
    OSExecute.command(
     "java net.mindview.atunit.AtUnit AtUnitExternalTest");
  }
} /* Output:
annotations.AtUnitExternalTest
  . _methodOne
  . _methodTwo This is methodTwo

OK (2 tests)
*///:~
