//: annotations/SimulatingNull.java
package annotations;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/***
 *
 * 624页
 * 在定义注解的时候，这算得上是一个习惯用法，
 *
 * 编译器对元素的默认值有些过分挑剔，首先，元素不能有不确定的值，也就是说，元素必须要么具有默认值，要么在使用注解时
 * 提供元素的值
 * 其次，对于晨基本类型的元素，无论是在源代码中声明时，或是在注解接口中定义默认值时，都不能以null作为基本的值，这个
 * 约束使得处理器很难表现一个元素的存在或缺失的状态，因为在每个注解的声明中，所有的元素都存在，并且都具有相应的值，
 * 为了绕开这约束，我们只能自己定义一引进特殊的值，例如空字符串或负数，以此表示某个元素不存在。
 *
 *
 *
 *
 *
 * 在定义的时候，这算得上是一个比较通用的用法。
 *
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SimulatingNull {
  public int id() default -1;

  public String description() default "";
} ///:~
