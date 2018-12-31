//: annotations/InterfaceExtractorProcessor.java
// APT-based annotation processing.
// {Exec: apt -factory
// annotations.InterfaceExtractorProcessorFactory
// Multiplier.java -s ../annotations}
package annotations;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.declaration.Modifier;
import com.sun.mirror.declaration.ParameterDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


/**
 * 631 页
 *
 * 所有的工作都在process()方法中完成，在分析一个类的时候，我们用MethodDeclaration类以及其上的getMOdifiers()方法
 * 业找到public方法，不包括static的那些，一旦找到我们所需的pulic 方法，就将其保存在一个ArrayList中，然后在一个.java
 * 文件中，创建新的接口的方法定义。
 *
 * 注意，处理器类的构造器以AnnotationProcessorEnvironment对象为参数，通过该对象，我们就能知道apt正在处理的所有的类型，
 * 类定义，并且可以通过它获得Messager对象和Filer对象，Messager对象可以用来向用户报告信息，比如处理过程中，发生的任何的
 * 错误，以及错误的源代码中出现的位置等，Filer是一种PrintWriter，我们可通过它创建新的文件，不使用普通的PrintWriter而使用
 * Filer对象的主要原因是，只有这样apt才能知道我们创建的新文件，从而对新文件进行注解处理，并且在需要的时候编译它们。
 * 同时我们看到，Filer的createSourceFile()方法以将要新创建的新文件，从而对桨文件进行注解处理，并且在需要的时候编译它们。
 * 同时我们看到，Filer的CreatekSourceFile() 方法以将要新建的类或接口的名字，打开了一个普通的输出流，现在还没有什么工具
 * 帮助程序员创建Java语言结构，所以我们只能用基本的Print()和Println()方法业生成Java源代码，因此，你必须小心的仔细的处理括号
 * 确保其闭合，并且确保生成的代码语法正确
 *
 *
 */
public class InterfaceExtractorProcessor implements AnnotationProcessor {
    private final AnnotationProcessorEnvironment env;
    private ArrayList<MethodDeclaration> interfaceMethods =
            new ArrayList<MethodDeclaration>();

    public InterfaceExtractorProcessor(
            AnnotationProcessorEnvironment env) {
        this.env = env;
    }

    public void process() {
        for (TypeDeclaration typeDecl :
                env.getSpecifiedTypeDeclarations()) {
            ExtractInterface annot =
                    typeDecl.getAnnotation(ExtractInterface.class);
            if (annot == null)
                break;
            for (MethodDeclaration m : typeDecl.getMethods())
                if (m.getModifiers().contains(Modifier.PUBLIC) &&
                        !(m.getModifiers().contains(Modifier.STATIC)))
                    interfaceMethods.add(m);
            if (interfaceMethods.size() > 0) {
                try {
                    PrintWriter writer =
                            env.getFiler().createSourceFile(annot.value());
                    writer.println("package " +
                            typeDecl.getPackage().getQualifiedName() + ";");
                    writer.println("public interface " +
                            annot.value() + " {");
                    for (MethodDeclaration m : interfaceMethods) {
                        writer.print("  public ");
                        writer.print(m.getReturnType() + " ");
                        writer.print(m.getSimpleName() + " (");
                        int i = 0;
                        for (ParameterDeclaration parm :
                                m.getParameters()) {
                            writer.print(parm.getType() + " " +
                                    parm.getSimpleName());
                            if (++i < m.getParameters().size())
                                writer.print(", ");
                        }
                        writer.println(");");
                    }
                    writer.println("}");
                    writer.close();
                } catch (IOException ioe) {
                    throw new RuntimeException(ioe);
                }
            }
        }
    }
} ///:~
