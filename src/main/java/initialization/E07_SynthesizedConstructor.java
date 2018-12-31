
//: initialization/E07_SynthesizedConstructor.java
/****************** Exercise 7 ******************
 * Create a class without a constructor, then
 * create an object of that class in main() to
 * verify that the default constructor is
 * automatically synthesized.
 ************************************************/
package initialization;


/****
 * 83页
 * 创建一个没有构造器的类，并在main()中创建对象，用以验证编译器是否真的加入了默认的构造器。
 *
 *
 *
 * 1
 */
public class E07_SynthesizedConstructor {
    public static void main(String args[]) {
        // Call the synthesized default constructor
        // for this class:
        new E07_SynthesizedConstructor();
    }
} 