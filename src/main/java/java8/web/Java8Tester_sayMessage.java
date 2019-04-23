package java8.web;


/***
 * lambda 表达式只能引用标记了 final 的外层局部变量，这就是说不能在lambda 内部修改定义在域外的局部变量，否则会编译错误。
 */
public class Java8Tester_sayMessage {
    final static String salutation = "Hello! ";

    public static void main(String args[]) {
        GreetingService greetService1 = message ->
                System.out.println(salutation + message);
        greetService1.sayMessage("Runoob");
        //====================相当于下面==============================
        GreetingService g = new GreetingService() {
            @Override
            public void sayMessage(String message) {
                //salutation = salutation+1;
                System.out.println(salutation + message);
            }
        };
        g.sayMessage("jack");
        //===========================================================
    }

    interface GreetingService {
        void sayMessage(String message);
    }
}
