package polymorphism;//: polymorphism/FieldAccess.java
// Direct field access is determined at compile time.

/****
 *
 * 157页
 *  它自己和Sub.fidld分配了不同的存储空间，这样，Sub实际上包含两个称为field的域
 *  ，它自己的和它从Super处得到的，然而，在引用Sub中的field时所产生的默认域并非Super版本的field哉，因此，为了得
 *  到Super.field，必须显式的指明 super.field
 *
 *
 *
 *
 */
class Super {
    public int field = 0;

    public int getField() {
        return field;
    }
}

class Sub extends Super {
    public int field = 1;

    public int getField() {
        return field;
    }

    public int getSuperField() {
        return super.field;
    }
}

public class FieldAccess {
    public static void main(String[] args) {
        Super sup = new Sub(); // Upcast
        System.out.println("sup.field = " + sup.field +
                ", sup.getField() = " + sup.getField());
        Sub sub = new Sub();
        System.out.println("sub.field = " +
                sub.field + ", sub.getField() = " +
                sub.getField() +
                ", sub.getSuperField() = " +
                sub.getSuperField());
    }
} /* Output:
sup.field = 0, sup.getField() = 1
sub.field = 1, sub.getField() = 1, sub.getSuperField() = 0
*///:~
