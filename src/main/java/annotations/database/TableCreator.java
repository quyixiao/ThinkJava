//: annotations/database/TableCreator.java
// Reflection-based annotation processor.
// {Args: annotations.database.Member}
package annotations.database;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/***
 *  不能使用关键字extends 来继承某个 @interface , 这真是一个遗憾，如果可以定义一个@TableColumn注解（参考以前的建议）
 *  ，同时在其中嵌套一个@SQLType类型的注解，那么这将成为一个优雅的设计，按照这种方式，程序员可以继承的话，这将大大的
 *  减少程序员打字的工作量，并且使语法更整洁，在Java 未来版本中，似乎没有任何关于让注解支持继承的提案，所以在当前状况
 *  下，上例中的解决方案可能己经是最佳的解决方案
 *
 *
 *
 *
 *
 *  实现处理器
 *  下面是一个注解处理器的例子，它将读取一个类文件，检查其上的数据库注解，并生成用来创建数据库的sql 命令
 *
 *
 *
 * main() 方法会处理命令行传入的每一个类名，使用forName() 方法加载每一个类，并使用getAnnotation(DB)检查该类是
 * 否带有@DBTable 注解，如果有，就将发现的表名保存下来，然后读取这个类的所有的域，并表名保存下来，然后读取这个类的所
 * 有域，并用getDeclaredAnnotation()进行检查，该方法返回一个包含一个@SQLString 类型，如果是的话，在对应的处理块中
 * 将构造出相应的cloumn名的字符串片断，注意，@SQLString类型，如果是的话，在对应的由于注解没有继承机制，所以要获得
 * 近似多态的行为，使用getDeclaredAnnotaion()唯一的办法，嵌套中的@constraint 注解被传递给getConstraints()方法
 * ，由它负责构造一个包含SQL约束的String对象。
 *
 *
 *
 * 需要提醒读者的是，上面演示的技巧于真实的对象/关系映射而言，是很幼稚的，例如使用@DBTable类型的注解，程序员以参数的
 * 形式给出表的名字，如果的话，在对应的处理块中将构造出相应的coumn名的字符串片断，注意，由于注解没有继承机制，所以要
 * 获得近似多态的行为，使用getDEclaredAnnotioan()是唯一的办法的String对象。
 * 需要提醒的读者的是，上面演示的技巧对真实/关系映射而言，是很幼稚的，例如使用@DBTable类型的注解，程序员以参数的形式给
 * 出表的名字，如果已经有了很多可用的framework，可以将对象映射到关系数据库，并且，其中越来越多的framework已经开始利用
 *
 *
 *
 *
 *
 *
 *
 */
public class TableCreator {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("arguments: annotated classes");
            System.exit(0);
        }
        for (String className : args) {
            Class<?> cl = Class.forName(className);
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            if (dbTable == null) {
                System.out.println(
                        "No DBTable annotations in class " + className);
                continue;
            }
            String tableName = dbTable.name();
            // If the name is empty, use the Class name:
            if (tableName.length() < 1)
                tableName = cl.getName().toUpperCase();
            List<String> columnDefs = new ArrayList<String>();
            for (Field field : cl.getDeclaredFields()) {
                String columnName = null;
                Annotation[] anns = field.getDeclaredAnnotations();
                if (anns.length < 1)
                    continue; // Not a db table column
                if (anns[0] instanceof SQLInteger) {
                    SQLInteger sInt = (SQLInteger) anns[0];
                    // Use field name if name not specified
                    if (sInt.name().length() < 1)
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = sInt.name();
                    columnDefs.add(columnName + " INT" +
                            getConstraints(sInt.constraints()));
                }
                if (anns[0] instanceof SQLString) {
                    SQLString sString = (SQLString) anns[0];
                    // Use field name if name not specified.
                    if (sString.name().length() < 1)
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = sString.name();
                    columnDefs.add(columnName + " VARCHAR(" +
                            sString.value() + ")" +
                            getConstraints(sString.constraints()));
                }
                StringBuilder createCommand = new StringBuilder(
                        "CREATE TABLE " + tableName + "(");
                for (String columnDef : columnDefs)
                    createCommand.append("\n    " + columnDef + ",");
                // Remove trailing comma
                String tableCreate = createCommand.substring(
                        0, createCommand.length() - 1) + ");";
                System.out.println("Table Creation SQL for " +
                        className + " is :\n" + tableCreate);
            }
        }
    }

    private static String getConstraints(Constraints con) {
        String constraints = "";
        if (!con.allowNull())
            constraints += " NOT NULL";
        if (con.primaryKey())
            constraints += " PRIMARY KEY";
        if (con.unique())
            constraints += " UNIQUE";
        return constraints;
    }
}









/* Output:
Table Creation SQL for annotations.database.Member is :
CREATE TABLE MEMBER(
    FIRSTNAME VARCHAR(30));
Table Creation SQL for annotations.database.Member is :
CREATE TABLE MEMBER(
    FIRSTNAME VARCHAR(30),
    LASTNAME VARCHAR(50));
Table Creation SQL for annotations.database.Member is :
CREATE TABLE MEMBER(
    FIRSTNAME VARCHAR(30),
    LASTNAME VARCHAR(50),
    AGE INT);
Table Creation SQL for annotations.database.Member is :
CREATE TABLE MEMBER(
    FIRSTNAME VARCHAR(30),
    LASTNAME VARCHAR(50),
    AGE INT,
    HANDLE VARCHAR(30) PRIMARY KEY);
*///:~
