package java8;

public class Test2 {


    public void myTest(MyInterface myInterface) {
        System.out.println(1);
        myInterface.test();
        System.out.println(2);
    }


    public static void main(String[] args) {

        Test2 test2 = new Test2();


        MyInterface myInterface = new MyInterface() {
            @Override
            public void test() {
                System.out.println("test1");
            }
        };


        test2.myTest(myInterface);
        System.out.println(myInterface.getClass());
        System.out.println(myInterface.getClass().getSuperclass());
        System.out.println(myInterface.getClass().getInterfaces()[0]);


    }


}
