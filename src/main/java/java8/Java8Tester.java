package java8;


/***
 *
 Predicate<T>——接收T对象并返回boolean
 Consumer<T>——接收T对象，不返回值
 Function<T, R>——接收T对象，返回R对象
 Supplier<T>——提供T对象（例如工厂），不接收值
 UnaryOperator<T>——接收T对象，返回T对象
 BinaryOperator<T>——接收两个T对象，返回T对象


 */
public class Java8Tester {
    public static void main(String args[]) {
        Java8Tester tester = new Java8Tester();
// 类型声明
        MathOperation addition = (int a, int b) -> a + b;
// 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;
// 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };
// 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;
        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));
// 不用括号
        GreetingService greetService1 = new GreetingService() {
            @Override
            public void sayMessage(String message) {
                System.out.println("Hello " + message);
            }
        };
// 用括号
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);
        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }
}