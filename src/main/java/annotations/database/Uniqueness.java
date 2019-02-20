//: annotations/database/Uniqueness.java
// Sample of nested annotations
package annotations.database;


/****
 *
 *
 *
 * 625 页
 *
 * 下面是一个简单的定义，我们其中应用了以上这些注解，
 *
 *
 *
 *
 * 1
 *
 *
 */
public @interface Uniqueness {
    Constraints constraints()
            default @Constraints(unique = true,allowNull=false);
} ///:~
