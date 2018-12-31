//: exceptions/E30_Human.java
/****************** Exercise 30 ******************
 * Modify main() in Human.java so that the
 * technique in TurnOffChecking.java is used to
 * handle the different types of exceptions.
 ***********************************************/
package exceptions;


/******
 *
 * 281页
 *
 * 修改Human.java，使异常继承自RunTimeException修改main()，使其用
 * TurnOffChecking.java处理不同的类型异常
 *
 *
 *
 * 在恰当的级别处理问题（在知道该如何处理的情况下才捕获异常）
 * 解决问题并且重新调用产生异常的方法
 * 进行少许修补，然后绕过异常发生的专访继续执行
 * 用别的数据进行计算，以代替方法预计会返回的值
 * 把当前运行环境下能做的事情尽量的做完，然后把相同的异常重抛到更高层
 * 把当前的运行环境下能做的事情尽量做完，然后把不同的异常抛到更高层
 * 终止程序
 * 进行简化（如果你的异常模式使用问题变得太复杂，那用起来会非常痛苦也很烦人）
 * 让类库和程序更安全，（这既是在为调试做短期投资，也是在为程序的健壮性做长期投资）
 *
 */
public class E30_Human {
    static void throwRuntimeException(int type) {
        try {
            switch (type) {
                case 0:
                    throw new Annoyance();
                case 1:
                    throw new Sneeze();
                default:
                    return;
            }
        } catch (Exception e) { // Adapt to unchecked:
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        // Let RuntimeExceptions go out of the method:
        throwRuntimeException(2);
        // Or let catch exceptions:
        for (int i = 0; i < 2; i++)
            try {
                throwRuntimeException(i);
            } catch (RuntimeException re) {
                try {
                    throw re.getCause();


                } catch (Sneeze e) {
                    System.out.println("Caught Sneeze");
                } catch (Annoyance e) {
                    System.out.println("Caught Annoyance");
                } catch (Throwable t) {
                    System.out.println(t);
                }
            }
    }
}