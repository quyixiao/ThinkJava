//: exceptions/E28_RuntimeExceptionClass.java
// {ThrowsException}
/****************** Exercise 28 ******************
 * Modify Exercise 4 so that the custom exception
 * class inherits from RuntimeException, and show
 248
 Thinking in Java, 4th Edition Annotated Solution Guide
 * that the compiler allows you to leave out the
 * try block.
 ***********************************************/
package exceptions;


/******
 *
 * 281页
 *  修改练习4，全使客户的异常类继承RunTimeException ,并展示编译器允许你省略try语句块
 *
 *
 * 1
 */
class MyRuntimeException extends RuntimeException {
    public MyRuntimeException(String s) {

        super(s);


    }
}

public class E28_RuntimeExceptionClass {
    public static void main(String args[]) {


        throw new MyRuntimeException("MyRuntimeException msg");


    }
} 