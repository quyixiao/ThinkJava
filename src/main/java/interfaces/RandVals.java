package interfaces;//: interfaces/RandVals.java
// Initializing interface fields with
// non-constant initializers.

import java.util.Random;

/***
 *
 *
 * 184
 *
 * 在接口中的定义域不能是空final，但是可以被非常量表达式初始化，例如
 *
 */
public interface RandVals {
    Random RAND = new Random(47);
    int RANDOM_INT = RAND.nextInt(10);
    long RANDOM_LONG = RAND.nextLong() * 10;
    float RANDOM_FLOAT = RAND.nextLong() * 10;
    double RANDOM_DOUBLE = RAND.nextDouble() * 10;
} ///:~
