package holding;//: holding/ApplesAndOrangesWithGenerics.java

import java.util.ArrayList;


/****
 *
 * 217页
 *
 * @符号开头，可以的接受参数，这里的suppressWarnings注解及其参数
 * 只表示只有有关，不受检查异常，的警告信息应该被抑制
 *
 *
 * 1
 */
public class ApplesAndOrangesWithGenerics {
    public static void main(String[] args) {
        ArrayList<Apple> apples = new ArrayList<Apple>();
        for (int i = 0; i < 3; i++)
            apples.add(new Apple());
        // Compile-time error:
        // apples.add(new Orange());
        for (int i = 0; i < apples.size(); i++)
            System.out.println(apples.get(i).id());
        // Using foreach:
        for (Apple c : apples)
            System.out.println(c.id());
    }
} /* Output:
0
1
2
0
1
2
*///:~
