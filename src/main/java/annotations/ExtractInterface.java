//: annotations/ExtractInterface.java
// APT-based annotation processing.
package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/***
 * 使用apt处理注解
 *
 * 注解处理工具apt,这是Sum为了帮助注解处理过程而提供的工具，由于该工具的第一版，其功能还比较基础，不过
 * 它确实有助于程序员开发工作
 *
 *  与javac一样，apt被设计为操作的Java源文件，而不是编译后的类，默认情况下，apt会在处理完源文件后编译它们
 *  ，如果在系统构建过程中会自动创建一些新的源文件，那么这个特性非常有用，事实上，apt会检查新生成的源文件中
 *  注解，然后将所有文件一现编译。
 *  当注解处理器生成一个新的源文件时，该文件会在新一轮的注解处理中的接受检查，该工具会一轮一轮地处理，直到不
 *  再有新的源文件产生为止，然后它再编译所有的源文件。
 *
 *  程序员自定义的每一个注解都需要自己的处理器，而apt工具能够很容易的将多个注解处理器组合在一起，有了它，程序员就可以
 *  指定多个要处理的类，这比程序员自己遍历所有的类文件简单多了，此外还可以添加监听器，并在一轮注解处理结束的时候
 *  收到通知信息。
 *  在撰写本章的时候，apt还不是一个正式的Ant任务，不过显然可以将其作为一个Ant的外部任务运行，要想编译这一节中出现的
 *  注解处理器，你必须将tools.jar设置在你的ClassPath中，这个工具类库同时还包含了con.sum.minroor.*接口
 *  通过使用AnnotationPrrocessorFactory, apt 能够为每一个它发现的的注解生成一个正确的注解处理器，当你使用apt的时候
 *  ，必须指明一个工厂类，或者指明能找到apt所需的工厂类路径。否则，apt会踏上一个神秘的探索之旅。
 *   详细的信息可能在sum文档中的，"开发一个注解处理器"一节中找到。
 *
 *   使用apt生成注解处理器时，我们无法利用Java反射机制，因为我们操作的没有源代码，而不是编译后的类，使用mirror API
 *   能够解决这个问题，它使我们能够在未经编译中的没源代码中查看方法，域以及类型。
 *   下面是一个自定义的注解，使用它可以把一个类中的public方法提取出来，构造成一个新的接口。
 *
 *
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ExtractInterface {
    public String value();
} ///:~
