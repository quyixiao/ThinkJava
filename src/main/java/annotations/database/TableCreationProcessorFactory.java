//: annotations/database/TableCreationProcessorFactory.java
// The database example using Visitor.
// {Exec: apt -factory
// annotations.database.TableCreationProcessorFactory
// database/Member.java -s database}
package annotations.database;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;
import com.sun.mirror.declaration.ClassDeclaration;
import com.sun.mirror.declaration.FieldDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;
import com.sun.mirror.util.SimpleDeclarationVisitor;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import static com.sun.mirror.util.DeclarationVisitors.NO_OP;
import static com.sun.mirror.util.DeclarationVisitors.getDeclarationScanner;


/****
 *
 *
 * 632 页
 *  上面的例子中是一个相当的简单的注解处理器，只需对一个注解进行分析，但我们仍然要做大量的复杂的工作。
 *  因此，处理注解的真实的过程可能会非常的复杂，当我们有更多的注解和更多的处理器时，为了防止这种复杂的
 *  性迅速攀升，mirror api 提供了对访问者设计模式的支持，访问者是Gama等人所著的设计模式，一书中的经典
 *  设计模式之一，你也可以在 thinking in patterns 中找到更加详细的解释
 *
 *
 *  一个访问者会遍历某个数据结构或一个对象的集合，对其中的每一个对象执行一个操作，该数据结构无需有序，而你
 *  对每个对象添加新的操作，而无需得类的定义中添加方法。
 *
 *  这个技巧在处理器注解中非常有用，因为一个Java类可能看作是一系列的对象的集合，例如TypeDeclaretion 对象
 *  及 MethodDeclaration对象等，当你配合访问者模式使用apt工具时，需要提供一个Visitor类，它具有一个能够
 *  处理你要访问的各种声明的方法，然后，你就可以为方法，类以及域上的注解实现相应的处理方法行为。
 *  下面是sql表生成器的例子，不过这次我们使用访问者模式来创建工厂和注解处理器。
 *
 *
 *
 *
 *
 *
 */
public class TableCreationProcessorFactory implements AnnotationProcessorFactory {
    public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> atds,
                                               AnnotationProcessorEnvironment env) {
        return new TableCreationProcessor(env);
    }

    public Collection<String> supportedAnnotationTypes() {
        return Arrays.asList("annotations.database.DBTable", "annotations.database.Constraints",
                "annotations.database.SQLString", "annotations.database.SQLInteger");
    }

    public Collection<String> supportedOptions() {
        return Collections.emptySet();
    }

    private static class TableCreationProcessor implements AnnotationProcessor {
        private final AnnotationProcessorEnvironment env;
        private String sql = "";

        public TableCreationProcessor(AnnotationProcessorEnvironment env) {
            this.env = env;
        }

        public void process() {
            for (TypeDeclaration typeDecl : env.getSpecifiedTypeDeclarations()) {
                typeDecl.accept(getDeclarationScanner(new TableCreationVisitor(), NO_OP));
                sql = sql.substring(0, sql.length() - 1) + ");";
                System.out.println("creation SQL is :\n" + sql);
                sql = "";
            }
        }

        private class TableCreationVisitor extends SimpleDeclarationVisitor {
            public void visitClassDeclaration(ClassDeclaration d) {
                DBTable dbTable = d.getAnnotation(DBTable.class);
                if (dbTable != null) {
                    sql += "CREATE TABLE ";
                    sql += (dbTable.name().length() < 1) ? d.getSimpleName().toUpperCase() : dbTable.name();
                    sql += " (";
                }
            }

            public void visitFieldDeclaration(FieldDeclaration d) {
                String columnName = "";
                if (d.getAnnotation(SQLInteger.class) != null) {
                    SQLInteger sInt = d.getAnnotation(SQLInteger.class);
                    // Use field name if name not specified
                    if (sInt.name().length() < 1)
                        columnName = d.getSimpleName().toUpperCase();
                    else
                        columnName = sInt.name();
                    sql += "\n    " + columnName + " INT" + getConstraints(sInt.constraints()) + ",";
                }
                if (d.getAnnotation(SQLString.class) != null) {
                    SQLString sString = d.getAnnotation(SQLString.class);
                    // Use field name if name not specified.
                    if (sString.name().length() < 1)
                        columnName = d.getSimpleName().toUpperCase();
                    else
                        columnName = sString.name();
                    sql += "\n    " + columnName + " VARCHAR(" + sString.value() + ")"
                            + getConstraints(sString.constraints()) + ",";
                }
            }

            private String getConstraints(Constraints con) {
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
    }
} /// :~
