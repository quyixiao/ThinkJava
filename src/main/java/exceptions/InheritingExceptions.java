package exceptions;//: exceptions/InheritingExceptions.java
// Creating your own exceptions.


/***
 *
 *
 * 251页
 *
 * 1
 *
 *
 *  建立新的异常类型最简单的方法就是让编译器为你产生默认构造器
 *
 *
 *  所以这里几乎不用写多少代码
 *
 *
 */
class SimpleException extends Exception {
}

public class InheritingExceptions {
    public void f() throws SimpleException {
        System.out.println("Throw SimpleException from f()");
        throw new SimpleException();
    }

    public static void main(String[] args) {
        InheritingExceptions sed = new InheritingExceptions();
        try {
            sed.f();
        } catch (SimpleException e) {
            System.out.println("Caught it!");
        }
    }
} /* Output:
Throw SimpleException from f()
Caught it!
*///:~
