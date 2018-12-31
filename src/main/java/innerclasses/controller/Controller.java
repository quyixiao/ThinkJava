//: innerclasses/controller/Controller.java
// The reusable framework for control systems.
package innerclasses.controller;

import java.util.ArrayList;
import java.util.List;


/**
 *
 *
 * 208页
 *
 *
 *
 *      请注意，在目前的设计中你并不知道Event到底做了什么，这正是此设计关键所在，使变化
 *  的事物与不变的事物相互分离，用我们话来说，变化的向量，就是各种不同的Event对象具有
 *  的不同的行为，而你通过创建不同的Evnet子类来说表现不同的行为
 *      这正是内部类要做的事情，内部类允许
 *      1）控制框架的完整实现是由单个的类创建的，从而使得实现的细节被封装起来，内部类
 *      用来表示解决问题所必需的各种不同的action()
 *      2)内部类能够很容易的访问外围类的任意成员，所以可以避免这种实现变得笨拙，如果没有这
 *      种能力，代码将变得令人讨厌，以至于你肯定选择别的方法
 *
 */
public class Controller {
    // A class from java.util to hold Event objects:
    private List<Event> eventList = new ArrayList<Event>();

    public void addEvent(Event c) {
        eventList.add(c);
    }

    public void run() {
        while (eventList.size() > 0)
            // Make a copy so you're not modifying the list
            // while you're selecting the elements in it:
            for (Event e : new ArrayList<Event>(eventList))
                if (e.ready()) {
                    System.out.println(e);
                    e.action();
                    eventList.remove(e);
                }
    }
} ///:~
