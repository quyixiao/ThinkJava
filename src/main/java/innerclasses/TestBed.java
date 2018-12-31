package innerclasses;//: innerclasses/TestBed.java
// Putting test code in a nested class.
// {main: TestBed$Tester}


/****
 *      我曾在本书中建议过，在每个类中都定一个main()方法，用来测试这个类，这样有一个缺点
 * 那就是必须带着那些己编译过的额外代码，如果这对你一个麻烦，那就可以使用嵌套类来放置测试代码
 *
 */
public class TestBed {
    public void f() {
        System.out.println("f()");
    }

    public static class Tester {
        public static void main(String[] args) {
            TestBed t = new TestBed();
            t.f();
        }
    }
} /* Output:
f()
*///:~
