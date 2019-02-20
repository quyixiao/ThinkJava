//: annotations/database/Member.java
package annotations.database;


/***
 * 626页
 *
 * 类的注解@DBTable给定了值 MEMBER，它将会用来你为表的名字，Bean的属性，FirstName
 * 和LastName ，都被注解为@SQLString， 并且其元素值创分别为30和50 ，这些注解有两个
 * 有趣的地方，第一，他们都使用了嵌入的2constraints注解的默认值，第二，它们都是使用了
 * 快捷方式，何谓快捷方式呢，如果程序员的注解中定义了名为value的元素，当然了，这也限制
 * 了程序员必须将此元素命名为value元素所需即可，不过在上面的例子中，这不但使主义清晰
 * ，而且这样的注解语句也更加的于理解
 * @SQLString(30)
 * 处理器将在创建表时候使用该值设置SQL列的大小
 * 默认值的语法虽然很灵巧，但它很快就变得复杂起来，以handle域的注解为例，这是一个@SQLString
 * 注解，同量该域将成为表主键，因此在嵌入的@Constraints注解中，必须对生PrimaryKey元素进行
 * 设定，这时事情就变得麻烦了，现在不得不使用很长的名-值对形式重新写出元素的名和@interface的
 * 名字，与此同时，由于在特殊命名的value元素己经不再是唯一的需要的元素，所以你也不能使用这种
 * 便捷的方式为其赋值了，如你所见，最终的结果算不上清晰易懂
 *
 *
 * 变通之道
 * 可以使用多种不同的方式来定义自己的注解，以实现上例的功能，例如，你可以使用一个单一的注解
 * 类@TableColume，这带有一个enum元素，该枚举类定义了String ，IntEger以及FLOAt等枚举实例
 * ，这就消除了每个SQL类型都需要一个@interface 定义的负担，不过也使得以额外的信息修饰SQL类型
 * 的需求变得不可能，而这些额外的信息修饰SQL 类型的需求变得不可能，而这些额外的信息，例如长度
 * 或精度等，可能是非常有必要的需求。
 *
 *
 * 我们也可以使用String元素来描述实际的sql类型，比如VARChAR(30)或 INTerGE，这使得程序员可以
 * 修饰SQL类型，但是，它同时也将Java类型到SQL类型的映射绑在一起，这可不是一个好的设计，我们可以
 * 希望更换数据库导致代码必须修改并重新编译，如果我们只需告诉注解处理器，我们正在使用的是什么，口
 * 味，的sql 然后由处理器为我们处理SQL类型的细节，那将是一个优雅的设计。
 * 第三种可行的方案是同时使用两个注解类型业注解一个域，@Constraints和相应的SQL类型（例如 @SQLIntge）
 * ，这种方式可能会使代码有点乱的，不过编译器允许程序员对一个目标同时使用多个注解，注意，使用多个注解
 * 的时候，同一个注解不能重复使用。
 *
 *
 * 注解不支持继承
 *
 *
 * 1
 *
 *
 */
@DBTable(name = "MEMBER")
public class Member {
    @SQLString(30)
    String firstName;
    @SQLString(50)
    String lastName;
    @SQLInteger
    Integer age;
    @SQLString(value = 30, constraints = @Constraints(primaryKey = true))
    String handle;
    static int memberCount;

    public String getHandle() {
        return handle;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String toString() {
        return handle;
    }

    public Integer getAge() {
        return age;
    }
} ///:~
