package typeinfo;//: typeinfo/SweetShop.java
// Examination of the way the class loader works.
import static net.mindview.util.Print.*;


/****
 *
 *     一旦某个类的Class对象被载入内存，它就被用来创建这相类的所有对象，下面的示范程序可
 * 以证明这一点
 *
 *
 *
 *   1
 *
 */
class Candy {
  static { print("Loading Candy"); }
}

class Gum {
  static { print("Loading Gum"); }
}


/***
 *    如果类Gum还没有被加载就加载它，在加载的过程中，Gum的static子句被执行
 *
 *    如果你己经拥有了一个感兴趣的类型对象，那就可以通过调用getClass()方法来获取引用了，
 * 这个方法属于
 *
 */
class Cookie {
  static { print("Loading Cookie"); }
}

public class SweetShop {
  public static void main(String[] args) {	
    print("inside main");
    new Candy();
    print("After creating Candy");
    try {
      Class.forName("Gum");
    } catch(ClassNotFoundException e) {
      print("Couldn't find Gum");
    }
    print("After Class.forName(\"Gum\")");
    new Cookie();
    print("After creating Cookie");
  }
} /* Output:
inside main
Loading Candy
After creating Candy
Loading Gum
After Class.forName("Gum")
Loading Cookie
After creating Cookie
*///:~
