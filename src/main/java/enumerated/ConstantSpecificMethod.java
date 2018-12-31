package enumerated;//: enumerated/ConstantSpecificMethod.java

import java.text.DateFormat;
import java.util.Date;


/**
 *
 *
 *
 * 603页
 *
 *
 *
 * main 方法的最后部分说明，enum的每一个实例作为一个键，总是存在的，但是，如果你没有
 * 为这个键调用put() 方法来存入相应的值的话，其对应值就是null。
 * 与常量方法相比，EnumMap有一个优点，那EnumMap，允许程序员改变对象，而常量相关的方法在
 * 编译期就被固定了。
 * 稍后你看到，在你有多种类型的enum,而且它们之间存在互操作的情况下，我们可以用EnumMap实现多
 * 路分发。
 *
 *
 *
 */
public enum ConstantSpecificMethod {
    DATE_TIME {
        String getInfo() {
            return
                    DateFormat.getDateInstance().format(new Date());
        }
    },
    CLASSPATH {
        String getInfo() {
            return System.getenv("CLASSPATH");
        }
    },
    VERSION {
        String getInfo() {
            return System.getProperty("java.version");
        }
    };

    abstract String getInfo();

    public static void main(String[] args) {
        for (ConstantSpecificMethod csm : values()) {
            System.out.println(csm.getInfo());
        }
    }
} /* (Execute to see output) *///:~
