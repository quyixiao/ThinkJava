
//: initialization/E18_ObjectArray.java
/****************** Exercise 18 *****************
 * Create objects to attach to the array of
 * references for Exercise 17.
 ************************************************/
package initialization;


/***
 * 101页
 *  通过创建对象赋值给引用数组，从而完成前一个练习
 *
 */
public class E18_ObjectArray {
    public static void main(String args[]) {
        Test[] array = new Test[5];
        for (int i = 0; i < array.length; i++)
            array[i] = new Test(Integer.toString(i));
    }
}