package innerclasses;//: innerclasses/ClassInInterface.java
// {main: ClassInInterface$Test}


/*****
 *
 *
 * 202页
 *  正常情况下，你不能在接口内部放置任何代码，介嵌套改变什么为接口的一部分，你放到接口的
 *  任何烦都最自动的是public和static的，因为类是sattic的，只是将嵌套类置于接口的命名
 *  空间内，这并不违反接口的规则，你甚至可以在内部类中实现外围接口，就像下面这样
 *
 */

public interface ClassInInterface {
    void howdy();

    class Test implements ClassInInterface {
        public void howdy() {
            System.out.println("Howdy!");
        }

        public static void main(String[] args) {
            new Test().howdy();
        }
    }
} /* Output:
Howdy!
*///:~
