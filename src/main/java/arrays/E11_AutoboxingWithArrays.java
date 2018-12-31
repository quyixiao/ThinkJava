//: arrays/E11_AutoboxingWithArrays.java
// {CompileTimeError}
/****************** Exercise 11 *****************
 * Show that autoboxing doesn’t work with arrays.
 ************************************************/
package arrays;


/****
 * 450
 *
 *
 * 1
 */
public class E11_AutoboxingWithArrays {
    public static void main(String[] args) {
        int[] pa = {1, 2, 3, 4, 5};
       // Integer[] wa = pa;
        Integer[] wb = {1, 2, 3, 4, 5};
       // int[] pb = wb;
    }
}