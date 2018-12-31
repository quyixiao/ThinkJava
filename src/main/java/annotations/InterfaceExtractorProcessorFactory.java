//: annotations/InterfaceExtractorProcessorFactory.java
// APT-based annotation processing.
package annotations;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;


/***
 * 631 页
 *
 *
 * apt工具需要一个工厂类来为其指明正确的处理器，然后它才能调用处理器上的process()方法
 *
 *
 * AnnotationProccessorFactory 接口只有三个方法，如你所见，其中之一getProcessorFor()方法返回注解
 * 处理器，该方法以包类型声明的Set（使用apt工具时传入的Java类）以及AnnotationProcessorEnvironment
 * 对象为参数，将传入的处理器对象，另外两个方法是superteAnotaionType和supportedOptions() ，程序员可以
 * 通过它们检查一下，是否apt工具发现的所有的注解都有相应的处理器，是否所有的控制台输入的参数都是你提供支持
 * 的选项，其中supportedAnnotaionTypes()方法尤其重要，因为一旦在返回的String集合中没有你的注解的完整类
 * 名，apt就会抱怨没有找到相应的处理器，从而发出警告信息，然后什么也不做就退出。
 * 以上例子中的处理器与工厂类都是在annotations包中，在InterfaceExtractorProcessor.java开关的注释文字中
 * ，我根据anotations的目录结构，在exec中标记处给出了需要的从命令行输入的命令。它将告诉apt工具，使用上面的
 * 工厂类来处理中的println()语句，估计你己经能猜到最终生成的Imultiplier.java是什么样子了：
 *
 *
 * public interface Imultiplier{
 *      public int multply(int x,int y);
 * }
 *
 *
 *  apt 也会编译这个产生的文件，因此你将在相同的目录中看到Imultiplier.class文件
 *
 *
 *
 *
 *
 */

public class InterfaceExtractorProcessorFactory implements AnnotationProcessorFactory {
    public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> atds, AnnotationProcessorEnvironment env) {
        return new InterfaceExtractorProcessor(env);
    }

    public Collection<String> supportedAnnotationTypes() {
        return Collections.singleton("annotations.ExtractInterface");
    }

    public Collection<String> supportedOptions() {

        return Collections.emptySet();

    }
} ///:~
