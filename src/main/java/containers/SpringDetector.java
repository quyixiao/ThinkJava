package containers;//: containers/SpringDetector.java
// What will the weather be?

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import static net.mindview.util.Print.print;


/**
 * 正确的equals() 方法必须满足下列的5 个条件
 *
 * 1） 自反性，对于任意的x,x.eueals(x) 一定返回true
 * 2) 对称性，对于任意的x,和y ，如果x.equals(y) 那么 y.equals(x)
 * 3) 传递性。对任意的x,y,z ,如果x.equals(y),y.equels(z),x 一定equals(y)
 * 4) 一致性，对任意的x和y,如果x.equels(y) 的结果无论比较多少次，返回的结果都是一样的，要目是true,要目为false
 */
public class SpringDetector {
    // Uses a Groundhog or class derived from Groundhog:
    public static <T extends Groundhog>
    void detectSpring(Class<T> type) throws Exception {
        Constructor<T> ghog = type.getConstructor(int.class);
        Map<Groundhog, Prediction> map =
                new HashMap<Groundhog, Prediction>();
        for (int i = 0; i < 10; i++)
            map.put(ghog.newInstance(i), new Prediction());
        print("map = " + map);
        Groundhog gh = ghog.newInstance(3);
        print("Looking up prediction for " + gh);
        if (map.containsKey(gh))
            print(map.get(gh));
        else
            print("Key not found: " + gh);
    }

    public static void main(String[] args) throws Exception {
        detectSpring(Groundhog.class);
    }
} /* Output:
map = {Groundhog #3=Early Spring!, Groundhog #7=Early Spring!, Groundhog #5=Early Spring!, Groundhog #9=Six more weeks of Winter!, Groundhog #8=Six more weeks of Winter!, Groundhog #0=Six more weeks of Winter!, Groundhog #6=Early Spring!, Groundhog #4=Six more weeks of Winter!, Groundhog #1=Six more weeks of Winter!, Groundhog #2=Early Spring!}
Looking up prediction for Groundhog #3
Key not found: Groundhog #3
*///:~
