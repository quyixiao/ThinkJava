/****************** Exercise 17 *****************
 * Modify polymorphism/Frog.java so that it uses
 * try-finally to guarantee proper cleanup, and
 * show that this works even if you return from the
 * middle of the try-finally.
 ***********************************************/
package exceptions;

// Frog.dispose() is protected, cannot be called directly


/******
 *
 *
 *
 *
 * 268页
 *  修改polymorphism/Frag.jav，使其使用try-finally来保证正确的清理，并展示
 *  即使在try-finally的中间返回，它也可以起作用
 *  
 *
 *
 */
class Frog2 extends polymorphism.Frog {
    protected void dispose() {
        super.dispose();
    }
}

public class E17_Frog {
    public static void main(String[] args) {
        Frog2 frog = new Frog2();
        try {
            // No return in the middle...
        } finally {
            frog.dispose();
        }
        frog = new Frog2();
        try {
            // With return in the middle...
            return;
        } finally {
            frog.dispose();
        }
    }
}