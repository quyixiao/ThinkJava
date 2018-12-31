//: generics/decorator/Decoration.java
package generics.decorator;

import java.util.Date;


/****
 * 414
 *
 *
 * 当你观察混型的使用方式时，就会发现混型概念好像与装饰器经常用于满足各种可能的组合，而直接子类化会产生过多的类，
 * 因此是不实际的
 *
 *
 *
 * 1
 */
class Basic {
    private String value;

    public void set(String val) {
        value = val;
    }

    public String get() {
        return value;
    }
}

class Decorator extends Basic {
    protected Basic basic;

    public Decorator(Basic basic) {
        this.basic = basic;
    }

    public void set(String val) {
        basic.set(val);
    }

    public String get() {
        return basic.get();
    }
}


class SerialNumbered extends Decorator {
    private static long counter = 1;
    private final long serialNumber = counter++;

    public SerialNumbered(Basic basic) {
        super(basic);
    }

    public long getSerialNumber() {
        return serialNumber;
    }
}

class TimeStamped extends Decorator {
    private final long timeStamp;

    public TimeStamped(Basic basic) {
        super(basic);
        timeStamp = new Date().getTime();
    }

    public long getStamp() {
        return timeStamp;
    }
}


public class Decoration {
    public static void main(String[] args) {
        TimeStamped t = new TimeStamped(new Basic());
        TimeStamped t2 = new TimeStamped(new SerialNumbered(new Basic()));
        //! t2.getSerialNumber(); // Not available
        SerialNumbered s = new SerialNumbered(new Basic());
        SerialNumbered s2 = new SerialNumbered(new TimeStamped(new Basic()));
        //! s2.getStamp(); // Not available
    }
} ///:~
