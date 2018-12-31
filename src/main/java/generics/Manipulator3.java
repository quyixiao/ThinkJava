package generics;//: generics/Manipulator3.java


/***
 *  只有当你希望使用的类型参数比某个具体类型，以及它的所有的子类，
 *  更加泛化时，也就是说，当你希望代码以够嘴唇多个类工作时，使用泛型者有所帮助，因此，类型参数 和它们在有用的泛型代码中的应用，通常比
 *  简单的类替换要更得要
 */
class Manipulator3 {
    private HasF obj;

    public Manipulator3(HasF x) {
        obj = x;
    }

    public void manipulate() {
        obj.f();
    }
} ///:~
