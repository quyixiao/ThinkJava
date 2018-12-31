//: annotations/UseCase.java
package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/***
 *
 *
 * 621页
 *
 *
 *
 *  * 下面是一个简单的注解，我们可以通过它来跟踪一个项目中的用例，如果一个方法或一组方法实现了某个用例的需求，那么
 * 程序员可以为此方法加上该注解，于是，项目经理通过计算己经实现的用例，那么程序员可以为此方法加上注解，于是，项目
 * 经理通过计算已经实现的用例，就可以很好的掌控项目的进展，而如查要更新或修改系统的业务逻辑，则维护该项目的的开发
 * 人员也可以很容易地在代码中找到对应的用例
 *
 *
 * 注意，id和description 类似的方法定义，由于编译器会对id进行类型检查，因此将用例文档的追踪数据库与源代码相关是
 * 可靠的，description元素有一个default值，如果在注解某个方法时没有给出description 的值，则该注解的处理器就会
 * 使用此元素的默认值。
 *
 *

 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {
  public int id();
  public String description() default "no description";
} ///:~
