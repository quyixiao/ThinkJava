/****************** Exercise 17 *****************
 * Modify Exercise 16 so Frog overrides the
 * method definitions from the base class
 * (provides new definitions using the same
 * method signatures). Note what happens in
 * main().
 ***********************************************/
package reusing;

/****
 * 140页
 *  使Frog覆盖基类中的方法的定义，令新定义使用相同的方法特征签名
 *  请留心main()都发生了什么
 *
 *
 * 1
 *
 */
class Frog2 extends Amphibian {
    public void moveInWater() {
        System.out.println(this.getClass().getSimpleName() + " Frog swimming");
    }

    public void moveOnLand() {
        System.out.println(this.getClass().getSimpleName() + " Frog jumping");
    }
}

public class E17_Frog2 {
    public static void main(String args[]) {
        Amphibian a = new Frog2();
        a.moveInWater();
        a.moveOnLand();
    }
}