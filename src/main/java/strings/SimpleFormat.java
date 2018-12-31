package strings;//: strings/SimpleFormat.java


/****
 * 290 页
 *
 *
 * 1
 *
 *
 *    可以看到，format()和printf()是等价的，它们只需要一个简单的格式化字符串，加上一串参数
 *  即可，第个参数对应一个格式修饰符
 *
 *
 */
public class SimpleFormat {
  public static void main(String[] args) {
    int x = 5;
    double y = 5.332542;
    // The old way:
    System.out.println("Row 1: [" + x + " " + y + "]");
    // The new way:
    System.out.format("Row 1: [%d %f]\n", x, y);
    // or
    System.out.printf("Row 1: [%d %f]\n", x, y);
  }
}


/* Output:
Row 1: [5 5.332542]
Row 1: [5 5.332542]
Row 1: [5 5.332542]
*///:~
