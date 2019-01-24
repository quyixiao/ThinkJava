package enumerated;//: enumerated/RandomTest.java

import net.mindview.util.Enums;


/*****
 *
 *
 * 597
 *
 *
 * 1
 * 下面是random方法的一个简单的示例
 *
 *
 *
 */
enum Activity {
    SITTING, LYING, STANDING, HOPPING,
    RUNNING, DODGING, JUMPING, FALLING, FLYING
}

public class RandomTest {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++)
            System.out.print(Enums.random(Activity.class) + " ");

    }
}



/* Output:
STANDING FLYING RUNNING STANDING RUNNING STANDING LYING DODGING SITTING RUNNING HOPPING HOPPING HOPPING RUNNING STANDING LYING FALLING RUNNING FLYING LYING
*///:~
