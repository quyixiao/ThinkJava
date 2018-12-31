package generics;//: generics/GenericMethods.java

/****
 *
 *      注意当使用泛型类型，时，必须在创建对象的时候指定类型参数的值，而使用泛型方法的时候
 *  通常不必指明参数类型，因为编译器会为我们指出具体的类型，这称为类型参数推断
 *
 */
public class GenericMethods {
    public <T> void f(T x) {


        System.out.println(x.getClass().getName());


    }

    public static void main(String[] args) {
        GenericMethods gm = new GenericMethods();
        gm.f("");
        gm.f(1);
        gm.f(1.0);
        gm.f(1.0F);
        gm.f('c');
        gm.f(gm);
    }
}



/* Output:
java.lang.String
java.lang.Integer
java.lang.Double
java.lang.Float
java.lang.Character
GenericMethods
*///:~
