//: annotations/AtUnitExample1.java
package annotations;

import net.atunit.Test;
import net.mindview.util.OSExecute;


/****
 *
 * 635页
 *
 * 单元测试是对类中的每一个方法提供一个或多个测试的一种实践，其目的是为了有规律的测试一个类的各个
 * 部分是否具备正确的行为，在Java中，最著名的单元测试就是Jnuit，在撰写本书时，Junit己经开始了向
 * Junit4更新过程，其目的正是为了融入注解，对于注解出现之前Jnuit而言，有一个主要的问题，即为了设置
 * 并运行jnuit测试需要做大量的工作，随着其渐渐的发展，这种负担己经减轻了一些，但注解的出现能够使其
 * 更加的贴近，最简单的单元测试系统。
 * 使用注解出现之前的Jnuit ，程序员必须创建一个独立的类来保存单元测试，有了注解，我们可能直接在要验证
 * 的类里编写测试，这将大大的减少单元测试所需的时间和麻烦之处，采用这种方式还有一个额外的好处，就是能够
 * 测试public方法一样很容易的测试private方法。
 *
 *
 *
 * 这个基于注解的测试构架叫做@Unit,其中最基本的测试形式，可能也是你用的最多的一个注解@Test,我们用@test
 * 来标测试方法，测试的方法不带参数，并返回boolean结果来说明测试的成功和失败，程序员可以任意命名他的测试方法
 * 同时，@Unit测试方法可以是任意你喜欢的的访问修饰方式，包括private
 *
 * 要使用@Unit，程序员必须引入 net.mindview.atunit,用@Unit的测试标记为合适的方法域打上标记，在接下来的
 * 例子中，你构建系统对编译后的类运行@Unit 下面是一个简单的例子
 *
 * 使用@Unit进行测试的类必须定义在某个包中，即必须包括package声明
 *
 * @Test 注解被置于methodOneTest() ，m2() ,m3() failureTest()以及annotherDisappointment()方法之前
 * ，它告诉@Nuit将这些方法作为单元测试来运行，同时，@Test验证并确保这些方法没有参数，并且返回值是boolean 或
 * void ,程序员编写的单元测试时，唯一要做的就是决定测试是成功还是失败，对于返回值为boolean的方法，应该返回true还是false
 *
 * 的测试，因此测试中的输出更是有用的，而且在最后，它还能告诉我们导致错误的类和测试
 * 程序员并非必须将测试用例方法嵌入在原本的类中，因为有时候这根本做不到，要生成一个非嵌入式的测试，最简单的办法就是继承。
 *
 *
 *
 */
public class AtUnitExample1 {
    public String methodOne() {
        return "This is methodOne";
    }

    public int methodTwo() {
        System.out.println("This is methodTwo");
        return 2;
    }

    @Test
    boolean methodOneTest() {
        return methodOne().equals("This is methodOne");
    }

    @Test
    boolean m2() {
        return methodTwo() == 2;
    }

    @Test
    private boolean m3() {
        return true;
    }

    // Shows output for failure:
    @Test
    boolean failureTest() {
        return false;
    }

    @Test
    boolean anotherDisappointment() {
        return false;
    }

    public static void main(String[] args) throws Exception {
        OSExecute.command(
                "java net.mindview.atunit.AtUnit AtUnitExample1");
    }
}





/* Output:
annotations.AtUnitExample1
  . methodOneTest
  . m2 This is methodTwo

  . m3
  . failureTest (failed)
  . anotherDisappointment (failed)
(5 tests)

>>> 2 FAILURES <<<
  annotations.AtUnitExample1: failureTest
  annotations.AtUnitExample1: anotherDisappointment
*///:~
