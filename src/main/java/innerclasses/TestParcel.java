package innerclasses;//: innerclasses/TestParcel.java

class Parcel4 {

    /****
     * 内部类PContents是private，所以除了Parcel4，没有人能访问它，PDestination是Protected，所以
     * 只有Parcel4及其子类 ，还有与Parcel4同一个包中的类，因为protected也给予了包访问权，能访问PDestionation
     * ，其他类都不能访问PDestination，这意味着，如果客户端程序员想也了解或访问这些成员，那是要受到限制的，实际上，
     * 甚至不能向下转型成private内部类，或Protected内部类，除非是继承自它的子类，因为不能访问其名字，就像在TestParcel类中看到的那样，于是
     * ，private内部类给类的设计都提供了一种途径，通过和种方式可能完全阻止任何依赖于类型编码，并且完全隐藏了实现的细节，此外，从客户端的程序
     * 员角度来看，由于不能访问任何新增加的，原本不属于公共接口的方法，所以的的扩展接口是没有价值的，这也给Jav编译器提供了生成更高效代码的机会
     *
     */
    private class PContents implements Contents {
        private int i = 11;

        public int value() {
            return i;
        }
    }

    protected class PDestination implements Destination {
        private String label;

        private PDestination(String whereTo) {
            label = whereTo;
        }

        public String readLabel() {
            return label;
        }
    }

    public Destination destination(String s) {
        return new PDestination(s);
    }

    public Contents contents() {
        return new PContents();
    }
}

public class TestParcel {
    public static void main(String[] args) {
        Parcel4 p = new Parcel4();
        Contents c = p.contents();
        Destination d = p.destination("Tasmania");
        // Illegal -- can't access private class:
        //! Parcel4.PContents pc = p.new PContents();
    }
} ///:~
