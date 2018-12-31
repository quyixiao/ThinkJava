package typeinfo;//: typeinfo/NullRobot.java
// Using a dynamic proxy to create a Null Object.

import net.mindview.util.Null;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.List;


/***
 *      假设存在许多不同的类型的Robot，我们想对每一种Robot类型都创建一个空对象，去执行某些特殊作
 *  在本例中，即提供空对象所代表的的Robot确切的类型信息，我些信息是通过动态代理捕获的
 *
 *
 *
 * 1
 */
class NullRobotProxyHandler implements InvocationHandler {
    private String nullName;
    private Robot proxied = new NRobot();

    NullRobotProxyHandler(Class<? extends Robot> type) {
        nullName = type.getSimpleName() + " NullRobot";
    }

    private class NRobot implements Null, Robot {
        public String name() {
            return nullName;
        }

        public String model() {
            return nullName;
        }

        public List<Operation> operations() {
            System.out.println("++++++++++++");
            return Collections.emptyList();
        }
    }

    public Object
    invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        return method.invoke(proxied, args);
    }
}

public class NullRobot {
    public static Robot
    newNullRobot(Class<? extends Robot> type) {
        return (Robot) Proxy.newProxyInstance(
                NullRobot.class.getClassLoader(),
                new Class[]{Null.class, Robot.class},
                new NullRobotProxyHandler(type));
    }

    public static void main(String[] args) {

        Robot[] bots = {
                new SnowRemovalRobot("SnowBee"),
                newNullRobot(SnowRemovalRobot.class)
        };

        for (Robot bot : bots) {
            System.out.println("==================");
            Robot.Test.test(bot);
        }
    }
}



/* Output:
Robot name: SnowBee
Robot model: SnowBot Series 11
SnowBee can shovel snow
SnowBee shoveling snow
SnowBee can chip ice
SnowBee chipping ice
SnowBee can clear the roof
SnowBee clearing roof
[Null Robot]
Robot name: SnowRemovalRobot NullRobot
Robot model: SnowRemovalRobot NullRobot
*///:~
