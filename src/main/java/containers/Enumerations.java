package containers;//: containers/Enumerations.java
// Java 1.0/1.1 Vector and Enumeration.

import net.mindview.util.Countries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;


/**
 * 521 页
 *
 * 迭代器发明了一个新的名字，枚举，取代了为人熟知的术语，此Enumeration 接口比Iterator小，只有两个名字很长的方法，一个为
 * boolean  hashMoreElements()  如果此枚举包含更多的元素，该方法就返回true,另一个为Object nextElment ，该方法返回
 * 此枚举中的下一个元素，如果还有的话，否则抛出异常
 *
 *
 * Enumeration 只是接口而不是实现，所以有时新的类库仍然使用旧的Enumeration，这个令人十分遗憾，但通常不会造成伤害
 * 虽然你的代码中应该使用了旧的Enumeration，这令人十分遗憾，但也得有所准备，类库可能会返回给你一个Enumeration
 * 此外，还可以通过使用Collections.enumerration() 方法来从Collection生成一个Enumration
 *
 *
 * 可以调用elements() 生成Enumeration ,然后使用它进行前序遍历，最后一行代码创，但也得有所准备，类库可能会返回给你
 * 一个Enumration
 *
 * 此外，还可以通过使用 Collections.enumerations() 方法来从Collection 生成一个Enumration，这样，即使有需要Enumeration 的旧
 * 代码，你仍然可以使用新容器
 *
 *
 * 前面在使用LinkedList时，己经介绍过 "栈" 的概念， 的stack 很奇怪，竟然不是用Vector 来构建Stack ，而是继承Vector ， 所以它拥有
 * 所有的特点和行为，再加上一些额外的Stack行为，很难了解设计者是否意识到这样的的特别的用处，或者只是一个幼稚的设计，唯一清楚的是，在
 * 匆忙的发布之前，没有经过仔细的审查，因此这个糟糕的设计仍然挂在这里（但是你永远都不应该使用它）
 *
 *
 *
 *
 *
 *
 *
 */
public class Enumerations {
  public static void main(String[] args) {
    Vector<String> v =
      new Vector<String>(Countries.names(10));
    Enumeration<String> e = v.elements();
    while(e.hasMoreElements()) {
      System.out.print(e.nextElement() + ", ");
    }
    // Produce an Enumeration from a Collection:
    e = Collections.enumeration(new ArrayList<String>());
  }
} /* Output:
ALGERIA, ANGOLA, BENIN, BOTSWANA, BULGARIA, BURKINA FASO, BURUNDI, CAMEROON, CAPE VERDE, CENTRAL AFRICAN REPUBLIC,
*///:~
