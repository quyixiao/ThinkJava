package enumerated;//: enumerated/OzWitch.java
// The witches in the land of Oz.

import static net.mindview.util.Print.print;

/**
 * 592 页
 *  一般来说，我们希望每个枚举实例能够返回对自身的描述，而不仅仅只是默认的toString() 实现，这
 *  只能返回枚举实例的名字，为此，你可以提供一个构造器，专门负责处理这个额外的信息，然后添加一个
 *  方法，返回这个描述信息，看看下面的示例
 *
 *
 * 注意：如果你打算定义自己的方法，那么必须在enum实例序列中添加一个分号，同时，Java 要求你必须定义
 * enum实例，如果在定义enum实例之前任何方法和属性，那么在编译时就会错误信息
 *
 * enum中构造器与方法和普通的类没有区别，除了少数限制以外，enum就是一个普通的类，所以，我们可能使用
 * enum 做了许多事情，虽然我们只使用的一般的普通的枚举类型
 *
 * 在这个例子中，虽然我们有意识地将enum的构造器声明为private ,但对于它的可访问性而言，其实并没有什么
 * 变化，因为（即使不声明为private） 我们只能在enum 定义的内部使用其构造器创建enum实例，一旦enum 的定
 * 义结束，编译器就不允许我们再使用其构造器来创建任何实例了
 *
 *
 *
 *
 *
 */
public enum OzWitch {
    // Instances must be defined first, before methods:
    WEST("Miss Gulch, aka the Wicked Witch of the West"),
    NORTH("Glinda, the Good Witch of the North"),
    EAST("Wicked Witch of the East, wearer of the Ruby " +
            "Slippers, crushed by Dorothy's house"),
    SOUTH("Good by inference, but missing");


    private String description;


    // Constructor must be package or private access:
    private OzWitch(String description) {

        this.description = description;

    }

    public String getDescription() {
        return description;
    }

    public static void main(String[] args) {
        for (OzWitch witch : OzWitch.values())
            print(witch + ": " + witch.getDescription());
    }
}


/* Output:
WEST: Miss Gulch, aka the Wicked Witch of the West
NORTH: Glinda, the Good Witch of the North
EAST: Wicked Witch of the East, wearer of the Ruby Slippers, crushed by Dorothy's house
SOUTH: Good by inference, but missing
*///:~
