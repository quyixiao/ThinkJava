package typeinfo;//: typeinfo/BoundedClassReferences.java


/***
 *
 *
 * 321
 *
 *
 *
 *
 * 1
 *
 *
 *
 */
public class BoundedClassReferences {
  public static void main(String[] args) {
    Class<? extends Number> bounded = int.class;
    bounded = double.class;
    bounded = Number.class;
    // Or anything else derived from Number.
  }
} ///:~
