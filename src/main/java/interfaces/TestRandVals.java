package interfaces;//: interfaces/TestRandVals.java

import static net.mindview.util.Print.print;


/**
 * 既然域是static的，它们就可以在类第一次被加载时初始化，这发生在任何域首次
 * 被访问时，这里给出了一个简单的测试
 *
 * 1
 *
 */
public class TestRandVals {
    public static void main(String[] args) {
        print(RandVals.RANDOM_INT);
        print(RandVals.RANDOM_LONG);
        print(RandVals.RANDOM_FLOAT);
        print(RandVals.RANDOM_DOUBLE);
    }
} /* Output:
8
-32032247016559954
-8.5939291E18
5.779976127815049
*///:~
