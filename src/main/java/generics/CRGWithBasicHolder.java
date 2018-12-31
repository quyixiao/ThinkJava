package generics;//: generics/CRGWithBasicHolder.java


/***
 * 405页
 *
 *
 *
 *      注意，这里有些东西很重要，新类Subtype接受的参数和返回的值具有的SubType类型而不仅仅是
 *  基类的BasicHolder类型，这就是CRG的本质，基类用导出类替代的参数，这就意味着泛型基类变成了一种其所有展出类的公共功能的
 *  模版，但是这些功能对于地其所有参数的返回值，将使用有确切类型而不是基类型，因此，在Subtype中，传递给set()的参数和从get()
 *  参数和从get()返回的类型都是确切的Subtype
 *
 *
 *
 *
 * 1
 */
class Subtype extends BasicHolder<Subtype> {



}

public class CRGWithBasicHolder {
    public static void main(String[] args) {
        Subtype st1 = new Subtype();
        Subtype st2 = new Subtype();
        st1.set(st2);
        Subtype st3 = st1.get();
        st1.f();
    }
} /* Output:
Subtype
*///:~
