//: generics/E26_CovariantArrays2.java
/****************** Exercise 26 *****************
 * Demonstrate array covariance using Numbers and
 * Integers.
 ************************************************/
package generics;

/****
 *
 *
 *
 * 391 使用Number和integer证明数组的协变性
 *
 *
 *
 * 1
 */
public class E26_CovariantArrays2 {
    public static void main(String[] args) {
        Number[] nums = new Integer[10];
        nums[0] = Integer.valueOf(1); // OK
        // Runtime type is Integer[], not Float[] or Byte[]:
        try {
            // Compiler allows you to add Float:
            nums[1] = new Float(1.0); // ArrayStoreException
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            // Compiler allows you to add Byte:
            nums[2] = Byte.valueOf((byte) 1);
            // Above line produces an ArrayStoreException
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}