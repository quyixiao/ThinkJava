package containers;//: containers/TestParam.java
// A "data transfer object."


/**
 * 500 页
 * <p>
 * <p>
 *
 *
 * 为了使用这个框架，你需要将待测容器以及Test对象列表传递给Tester.run()方法，这些都是重载的泛型的
 * 便利方法，它们可以减少在使用时必需的类型检查，Tester.run() 方法调用适当的重载构造器，然后调用
 * timeedTest() ，它会执行针对列表中的每一个元素每一个测试，timedTest() 会使用paramList相等的每一个
 * TestParam 对象进行重复测试，因为paramList 是从静态的defaultParams数组中初始化来的，因此你可以
 * 通过重新赋值deafaultPrams ,来修改用于所有测试的paramList ，或者可参通过传递针对某个测试的定制
 * 的paramList ,来修改用于该测试的paramList
 *
 *
 *
 *
 * 1
 */
public class TestParam {
    public final int size;
    public final int loops;

    public TestParam(int size, int loops) {
        this.size = size;
        this.loops = loops;
    }

    // Create an array of TestParam from a varargs sequence:
    public static TestParam[] array(int... values) {
        int size = values.length / 2;
        TestParam[] result = new TestParam[size];
        int n = 0;
        for (int i = 0; i < size; i++)
            result[i] = new TestParam(values[n++], values[n++]);
        return result;
    }

    // Convert a String array to a TestParam array:
    public static TestParam[] array(String[] values) {
        int[] vals = new int[values.length];
        for (int i = 0; i < vals.length; i++)
            vals[i] = Integer.decode(values[i]);
        return array(vals);
    }
} ///:~
