package reusing;


/****
 * 130页
 * 创建一个基类，它仅有一个非默认构造器，再创建一个导出类，它带有默认构造器
 * ，在展示出类的构造器中调用基类的构造器
 *
 *
 *
 *
 * 1
 */
public class E08_CallBaseConstructor {
    public static void main(String args[]) {
        new DerivedTwoConstructors();
        new DerivedTwoConstructors(74);
    }
}