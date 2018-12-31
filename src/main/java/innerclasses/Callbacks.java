//: innerclasses/Callbacks.java
// Using inner classes for callbacks
package innerclasses;

import static net.mindview.util.Print.print;


/*****
 *      java 最引人争议的问题之一就是，人们认为java应该包含某种类似指针的机制 ，以允许回调，通过
 *  回调，对象能够携带一些信息，对象稍后将会看到这是一个非常有用的概念，如果回调是通过指针实现的，那么
 *  只能寄希望于程序员不会调用该指针，然而，读者应该已经也了解到，java更小心仔细，所以没有在语言中包括
 *  指针
 *
 */
interface Incrementable {
    void increment();
}

// Very simple to just implement the interface:
class Callee1 implements Incrementable {
    private int i = 0;

    public void increment() {
        this.i++;
        print(this.i);
    }
}

class MyIncrement {
    public void increment() {
        print("Other operation");
    }

    static void f(MyIncrement mi) {
        mi.increment();
    }
}

// If your class must implement increment() in
// some other way, you must use an inner class:
class Callee2 extends MyIncrement {
    private int i = 0;

    public void increment() {
        super.increment();
        i++;
        print(i);
    }

    private class Closure implements Incrementable {
        public void increment() {
            // Specify outer-class method, otherwise
            // you'd get an infinite recursion:
            Callee2.this.increment();
        }
    }

    Incrementable getCallbackReference() {
        return new Closure();
    }
}

class Caller {
    private Incrementable callbackReference;

    Caller(Incrementable cbh) {
        callbackReference = cbh;
    }

    void go() {
        callbackReference.increment();
    }
}

public class Callbacks {
    public static void main(String[] args) {
        Callee1 c1 = new Callee1();
        Callee2 c2 = new Callee2();
        MyIncrement.f(c2);
        Caller caller1 = new Caller(c1);
        Caller caller2 = new Caller(c2.getCallbackReference());
        caller1.go();
        caller1.go();
        caller2.go();
        caller2.go();
    }
} /* Output:
Other operation
1
1
2
Other operation
2
Other operation
3
*///:~
