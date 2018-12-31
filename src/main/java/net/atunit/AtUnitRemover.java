//: net/mindview/atunit/AtUnitRemover.java
// Displays @Unit annotations in compiled class files. If
// first argument is "-r", @Unit annotations are removed.
// {Args: ..}
// {Requires: javassist.bytecode.ClassFile;
// You must install the Javassist library from
// http://sourceforge.net/projects/jboss/ }
package net.atunit;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.annotation.Annotation;
import net.mindview.util.BinaryFile;
import net.mindview.util.ProcessFiles;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import static net.mindview.util.Print.print;


/****
 * 647 页
 *
 *
 * 对许多项目而言，在发布的代码中是否保留测试代码并没有什么区别，（特别是你不想将你的所有的方法，测试方法都申明为private的情况下，如果你喜欢你就那么去做）
 * 但是在有的情况下，我们确实希望将测试代码清除掉，精简发布的程序，或者就是不希望测试代码暴露给用户
 * 与自己对手删除测试代码相比，这需要更复杂的字节码工程，不过开源的javassist工具类库将字节码工程带入了一个可
 * 行的领域，下面的各程序接受一个-r标志，作为其第一个参数 ，如果你提供了该标志，那么它就会删除所有的@test 注解，如果你没有提供该标记
 * ，那它则只会打印出@test注解，这里同样使用ProcessFile来遍历选择的文件和目录。
 *
 *
 *  ClassPool 是一种全景，它记录了你正在修改的系统中的所有的类，并能够保证所有的类在修改后的一致性。
 *  你必须从ClassPool中取得每个CtClass，这与使用类加载器和Class.forName（）向JVM加载类的方式类似。
 *
 *  CtClass包含的是类对象的字节码，你可以通过它取得类的相关的信息，并且操作类中的代码，在这里，我们调用getDeclaredMethods() ，与java反射
 *  机制一样，然后从每个CTMethod对象中取得一个MethodInfo对象。通过该对象，我们察看其中的注解信息，如果一个方法带有net.mindview.atunit
 *  包中的注解，就将该方法删除掉。
 *
 *  如果类被修改过，就用新的类覆盖原始的类文件。

 *  在撰写本书的时候，javaassitst刚刚加入了删除功能，同时我们发现，删除@TestProperty 域比删除方法复杂得多，因为，有些静态初始化的
 *  操作可能会引用这些域，所以你不能简单地将其删除，因此AtUnitRemover的当前版本只删除@Unit方法，不过，你应该查看一下javassit网站
 *  的更新，因为删除域功能以后可能实现，与此同时，对于AtUnitExternalText.java演示的外部测试方式，可能直接删除测试代码生成的类文件，
 *  从而到达删除所有的测试的目的。
 *
 *  注解是java引入的一项非常爱欢迎的补充，它提供了一种结构化的，并且具有类型检查的能力的新途径，从而使得程序员能够为代码加入元数据，而不会
 *  导致代码杂乱且难以阅读，使用注解能够帮助我们避免编写累赘的部署描述文件，以及其他生成的文件，而javadoc中的@deprecated 注解取代的事实
 *  也说明，与注释性文件相比，注解绝对更适合用于描述类相关的信息。
 *  java SET5 仅提供了很少的内置注解，这意味着如果你在别处找不到可用的类库，那就只能自己创建新的注解以及相应的处理器，有了apt工具，程序员可以
 *  编写新产生的源文件，以及简化构建过程，不过就目前的情况来看，mirror API 只能给予你一些基本的功能，帮助你找到java类定义中的元素，正如
 *  你己经看到的，Javassit能够用来操作字节码，或者你也可以编写自己的字节码操作工具。
 *  这个情况将来一定会改善,api提供认认真真，以及各各framework 一定会将注解包含在其中提供的工具集内，通过@Unit系统，我们可以想象得到
 *  ，注解很可能疳引发Java编程体验的巨大变化，
 *
 *
 *
 *
 *
 *
 *
 */
public class AtUnitRemover implements ProcessFiles.Strategy {
    private static boolean remove = false;

    public static void main(String[] args) throws Exception {
        if (args.length > 0 && args[0].equals("-r")) {
            remove = true;
            String[] nargs = new String[args.length - 1];
            System.arraycopy(args, 1, nargs, 0, nargs.length);
            args = nargs;
        }
        new ProcessFiles(new AtUnitRemover(), "class").start(args);
    }

    public void process(File cFile) {
        boolean modified = false;
        try {
            String cName = ClassNameFinder.thisClass(BinaryFile.read(cFile));
            if (!cName.contains(".")) {
                return; // Ignore unpackaged classes
            }
            ClassPool cPool = ClassPool.getDefault();

            CtClass ctClass = cPool.get(cName);

            for (CtMethod method : ctClass.getDeclaredMethods()) {
                MethodInfo mi = method.getMethodInfo();

                AnnotationsAttribute attr = (AnnotationsAttribute) mi.getAttribute(AnnotationsAttribute.visibleTag);

                if (attr == null) {
                    continue;
                }

                for (Annotation ann : attr.getAnnotations()) {
                    if (ann.getTypeName().startsWith("net.mindview.atunit")) {
                        print(ctClass.getName() + " Method: " + mi.getName() + " " + ann);
                        if (remove) {
                            ctClass.removeMethod(method);
                            modified = true;
                        }
                    }
                }
            }
            // Fields are not removed in this version (see text).
            if (modified) {
                ctClass.toBytecode(new DataOutputStream(new FileOutputStream(cFile)));
            }
            ctClass.detach();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
} ///:~
