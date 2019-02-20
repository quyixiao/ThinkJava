//: annotations/database/SQLInteger.java
package annotations.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/*****
 * 注解处理器通过@Constraints注解提取出数据库表的元数据，虽然，对于数据库所能提供的
 * 所有的约束而言，@Constaints注解只表示了它的一个很小的子集，不过它们所表达的思想已经
 * 很清楚了，primaryKey() ,allowNull()和unique()元素明智地提供了默认值，从而在大多数
 * 情况下，使用该注解的程序员无法输入太多东西
 *
 *
 * 另外两个@interface定义了是SQL类型的，如果希望这个framework更有价值的话，我们就应该
 * 为每种SQL类型都定义相应的注解，不过作为示例，两类类型足够了
 *
 * 这些SQL类型具有name()元素和constraints()元素，后者利用了嵌套注解的功能，将column类型的数据库约束信息
 * 嵌入其中，注意constraints()元素的默认值是@constraints,由于在@constraints注解类型之后，没有括号中的指明
 * @constrants中的元素， 如果要嵌入的@constraints注解的unique()元素为true ,并以此作为constraints()元素的默认值，则需要
 * 如下定义的该元素
 *
 *
 *
 *
 *
 * 1
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLInteger {
    String name() default "";

    Constraints constraints() default @Constraints;
} ///:~
