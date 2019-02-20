//: annotations/UseCaseTracker.java
package annotations;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/***
 *
 *
 * 623页
 * java目前只内置了三种标准注解（前面介绍过），以及四种元注解，元注解专职负责其他的注解
 *
 * @Target 表示该注解可以用于什么地方，可能的ElementType参数包括
 *        Constructor : 构造器声明
 *        Field ,域声明（包括enum实例）
 *        LoCAL_VARIABLE: 局部变量声明
 *        MeTHOD，方法声明
 *        PACKAge,包声明
 *        PARAMETER，参数声明
 *        TYPE，类，接口（包括注解类型） 或enum类型
 *
 * @REtetion  表示需要在在什么级别保存注解信息，可选的RetentionPolicy参数包括
 *            SOURCE :注解将被编译器丢弃
 *            CLASS：注解在Class文件中可能，但会被VM 丢弃
 *            RUNTIME,VM将在运动期保留注解，因此可以通过反射机制读取注解信息
 *
 *  @DOcumentd 将此注解包含在Javadoc中
 *
 *  @Inheried 允许子类继承父类的注解
 *
 *  大多数的时候，程序员主要是定义自己的注解，并编写自己的处理器，来处理它们。
 *
 *  如果没有用来读取注解的工具，那么注解也不比注释更加有用，使用注解的过程中，很重要的一个
 *  部分就是创建与使用注解处理器，JavaSet 5扩展反射机制的API,以帮助程序员构造这类工具。
 *  同时，它还提供了外部工具，apt 帮助程序员解析带来的注解的java源代码。
 *
 *  下面是一个非常简单的注解处理器，我们将用它来读取PassWrodUtils 类，并使用反射机制查找
 *  @UseCase标记，我们为其提供了一组id值，然后它会列出在PasswordUuilts中找到的用例以及
 *  缺失的用例
 *
 *
 *
 * 这个程序用到了两个反射的方法，getDeclaredMethods() 和 getAnnotation() ,它们都属于
 * AnnotateElement接口（Class，Method 与 Field 等类都实现了该接口），getAnonotaion()方法
 * 返回指定类的注解对象，在这里就是UseCase ,如果被注解在方法上滑该类型的注解，则返回null值，
 * 然后我们通过调用id()和descrioptn()方法从返回的UseCase对象中提取元素的值，其中，时。
 * 通过description() 方法取得的是默认值no descrition
 *
 *
 * 注解元素，标签 @UseCase 由UseCase.java 类定义，其中包含int 元素id, 以及一个String 元素description
 *  注解元素可用的类型如下所示
 * 所有的基本类型（int,float,boolean ）
 *  String ,Class ,enum,Annotation
 *
 *  以上的所有的数据类型的数组
 *
 *  如果你使用了其他的类型，那么编译器就会报错，注意，也不允许使用任何包装的类型，不过由于自动打包的存在
 *  ，这算不上什么限制，注解也可以作为元素的类型，也就是说注解可能嵌套稍后你就会看到，这是一个很有用的技巧
 *
 *
 *
 * 1
 *
 *
 *
 */
public class UseCaseTracker {
    public static void
    trackUseCases(List<Integer> useCases, Class<?> cl) {
        for (Method m : cl.getDeclaredMethods()) {
            UseCase uc = m.getAnnotation(UseCase.class);
            if (uc != null) {
                System.out.println("Found Use Case:" + uc.id() +
                        " " + uc.description());
                useCases.remove(new Integer(uc.id()));
            }
        }
        for (int i : useCases) {
            System.out.println("Warning: Missing use case-" + i);
        }
    }

    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<Integer>();
        Collections.addAll(useCases, 47, 48, 49, 50);
        trackUseCases(useCases, PasswordUtils.class);
    }
} /* Output:
Found Use Case:47 Passwords must contain at least one numeric
Found Use Case:48 no description
Found Use Case:49 New passwords can't equal previously used ones
Warning: Missing use case-50
*///:~
