package strings;//: strings/ScannerDelimiter.java

import java.util.Scanner;


/*****
 *
 *
 *
 *
 * 311页
 *      这个例子中使用逗号包括逗号前后的任意的空白字符，作为定界符，同样的技术也可以用来读取逗号分隔的文件
 *  我们可以使用useDelimiter()来设置定界符，同时，还有一个delimiter()方法，用来返回当前正在作为定界符
 *  使用的Patten对象
 *
 *
 *
 *
 */
public class ScannerDelimiter {
  public static void main(String[] args) {
    Scanner scanner = new Scanner("12, 42, 78, 99, 42");
    scanner.useDelimiter("\\s*,\\s*");
    while(scanner.hasNextInt())
      System.out.println(scanner.nextInt());
  }
}



/* Output:
12
42
78
99
42
*///:~
