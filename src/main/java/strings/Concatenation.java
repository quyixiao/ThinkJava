package strings;//: strings/Concatenation.java


/*****
 *
 *
 * 284页
 *
 *
 * 而java并不允许程序员重载任何操作符
 *
 *
 *
 *
 * 1
 *
 *
 *
 *
 * 从这个表中可吧看出，当需要改变字符串的内容时，String类的方法都会返回一个新的String对象，同时，如果内容发生改变，String
 * 的方法返回的是一个指向原对象的引用而已，这可以节约存储空间以及避免额外的开销
 *
 *
 *
 */
public class Concatenation {
  public static void main(String[] args) {
    String mango = "mango";
    String s = "abc" + mango + "def" + 47;
    System.out.println(s);
  }
} /* Output:
abcmangodef47
*///:~
