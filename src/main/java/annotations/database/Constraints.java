//: annotations/database/Constraints.java
package annotations.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/****
 *  625页
 *
 *  注解处理器通过 @Constraints注解提取出数据库的元数据，虽然对于数据库所能提供的所有约束而
 *  言，@constraints 注解只表示了它的一个很小的子集，不过它所要表达的思想已经很清楚了，PrimariKey()
 *  ，allowNull()和unique() 元素明智地提供了默认值，从而在大多数情况下，使用该注解的程序员无需输入太多的
 *  东西。
 *
 *
 *
 *
 *  另外两个@interface定义的是Sql类型，如果希望这个framework更有价值的话，我们就应该为每种数据库约束
 *  信息嵌入其中，注意constraints()元素的默认值是@Constraints, 由于在@Constrainsts 注解类型之后。
 *  没有任何中指明@Constraints注解，如果要令嵌入的@Constraint注解，如果要令嵌入的@constraints注解中的
 *  unique()元素为true, 并以此作为constrains()元素默认值，则需要如下的定义该元素
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Constraints {
    boolean primaryKey() default false;

    boolean allowNull() default true;

    boolean unique() default false;
} ///:~
