//: generics/E32_FixedSizeStackTest.java
/****************** Exercise 32 *****************
 * Verify that FixedSizeStack in GenericCast.java
 * generates exceptions if you try to go out of
 * its bounds. Does this mean that bounds-checking
 360
 Thinking in Java, 4th Edition Annotated Solution Guide
 * code is not required?
 ************************************************/
package generics;


/****
 *
 *
 * 403页
 *
 *  验证在GericCast.java中FixedSizeStack将产生异常，如果试图超出其边界的话
 *  这是否意味着边界检查代码是不是需要的
 *
 *
 * 1
 *
 *
 */
public class E32_FixedSizeStackTest {


    public static void main(String[] args) {
        FixedSizeStack<Integer> stack = new FixedSizeStack<Integer>(1);
        stack.push(1);
        System.out.println(stack.pop());
        try {
            stack.pop();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("======================" + e.toString());
        }
        stack = new FixedSizeStack<Integer>(1);
        stack.push(2);
        try {
            stack.push(2);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("+++++++++++++++++++++++++++" + e.toString());
        }
    }


}