//: annotations/database/DBTable.java
package annotations.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/***
 * 有些framework需要一些额外的信息才能与你协同工作，面这种情况最适合注解表现其价值了，像 Enterprise JavaBean 这样的
 * 技术，每一个bean 都需要大量的接口和部署来描述这个文件，而这具体细节都属于样板 文件，Web Service ,自定义标签以及对象
 * /关系映射工具，等，一般都需要XML描述文件，而这些描述文件脱离于源代码之外，因此，在定义了Java类之后，程序员还必须得忍受
 * 着沉闷，重复的提供某些信息，例如类名和包名等已经在原始类文件中提供的信息，每当程序员使用外部的描述文件的时候，他就拥有了
 * 同一个类的两个文件中提供的信息，这经常导致代码同步问题，同时，它也要求为项目工作的程序员，必须同时知道如何编写java程序
 * ，以及如何编写描述文件
 * 假设你希望提供一些基本的对象/关系映射的功能，能够自动生数据库，用以存储JavaBean对象，你可以选择使用XML文件，指明类的名字
 * ，每个成员以及数据库映射的相关信息，然而，如果使用注解的话，你可以将所有的信息都保存在JavaBean 源文件中，为此，我们需要一些
 * 新的注解，用以定义与Bean关联的数据库表的名字，以及与Bean的属性关联的列的名字和Sql 类型。
 * 以下是一个注解的定义，它告诉注解处理器，你需要为我生成一个数据库表。
 *
 * 在@Target 注解中指定的每一个ElementType就是一个约束，它告诉编译器，这个自定义的注解只能应用
 * 于该类型，程序员可以只指定 enum ElmentType中的某一个值，或者以逗号分隔的形式指定多个值，如果想要将注解应用于所有的ElemntType
 * ，那么可能省去 @Targe元注解，不过这并不常见
 *
 *
 * 注意： @DBTable有一个name()元素，该注解通过这个元素为处理器创建数据库表提供的。
 *
 *
 *
 *
 */
@Target(ElementType.TYPE) // Applies to classes only
@Retention(RetentionPolicy.RUNTIME)
public @interface DBTable {
    public String name() default "";
} ///:~
