package innerclasses;//: innerclasses/Parcel5.java
// Nesting a class within a method.


/****
 *  *
 *
 *  如前所示，你实现了某类型的接口，于是可以创建并返回对其的引用
 *  你要解决一个复杂的问题，想创建一个类来辅助你的解方案，但是又不希望这个类是公共可用的
 *
 * 一个定义在方法中的类
 * 一个定义在作用域的类，此作用域在方法的内部
 * 一个实现了接口的匿名类
 * 一个匿名类，它扩展了在非默认的构造器的类
 * 一个匿名类，它执行字段初始化
 * 一个匿名类，它通过实例初始化实现构造，匿名类不可能有构造器
 */
public class Parcel5 {
  public Destination destination(String s) {
    class PDestination implements Destination {
      private String label;
      private PDestination(String whereTo) {
        label = whereTo;
      }
      public String readLabel() { return label; }
    }
    return new PDestination(s);
  }
  public static void main(String[] args) {
    Parcel5 p = new Parcel5();
    Destination d = p.destination("Tasmania");
  }
} ///:~
