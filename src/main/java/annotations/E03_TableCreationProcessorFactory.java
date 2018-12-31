//: annotations/E03_TableCreationProcessorFactory.java
/****************** Exercise 03 *****************
 * Add support for more SQL types to
 * TableCreationProcessorFactory.java.
 ***********************************************/
package annotations;

import annotations.database.Constraints;
import annotations.database.DBTable;
import annotations.database.SQLInteger;
import annotations.database.SQLString;
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

public class E03_TableCreationProcessorFactory
        implements AnnotationProcessorFactory {
    public AnnotationProcessor getProcessorFor(
            Set<AnnotationTypeDeclaration> atds,
            AnnotationProcessorEnvironment env) {
        return new TableCreationProcessor(env);
    }

    public Collection<String> supportedAnnotationTypes() {
        return Arrays.asList(
                "annotations.database.DBTable",
                "annotations.database.Constraints",
                "annotations.database.SQLString",
                "annotations.database.SQLInteger",
                "annotations.SQLBoolean",
                "annotations.SQLCharacter");
    }

    public Collection<String> supportedOptions() {
        return Collections.emptySet();
    }

    private static class TableCreationProcessor
            implements AnnotationProcessor {
        private final AnnotationProcessorEnvironment env;
        private String sql = "";

        public TableCreationProcessor(
                AnnotationProcessorEnvironment env) {
            this.env = env;
        }

        public void process() {
            for (TypeDeclaration typeDecl :
                    env.getSpecifiedTypeDeclarations()) {
                typeDecl.accept(getDeclarationScanner(
                        new TableCreationVisitor(), NO_OP));
                sql = sql.substring(0, sql.length() - 1) + ");";
                System.out.println("creation SQL is :\n" + sql);
                sql = "";
            }
        }

        private class TableCreationVisitor
                extends SimpleDeclarationVisitor {
            public void visitClassDeclaration(
                    ClassDeclaration d) {
                DBTable dbTable = d.getAnnotation(DBTable.class);
                if (dbTable != null) {
                    sql += "CREATE TABLE ";
                    sql += (dbTable.name().length() < 1)
                            ? d.getSimpleName().toUpperCase()
                            : dbTable.name();
                    sql += " (";
                }
            }

            public void visitFieldDeclaration(
                    FieldDeclaration d) {
                String columnName = "";
                if (d.getAnnotation(SQLInteger.class) != null) {
                    SQLInteger sInt = d.getAnnotation(
                            SQLInteger.class);
                    // Use field name if name not specified
                    if (sInt.name().length() < 1)
                        columnName = d.getSimpleName().toUpperCase();
                    else
                        columnName = sInt.name();
                    sql += "\n    " + columnName + " INT" +
                            getConstraints(sInt.constraints()) + ",";
                }
                if (d.getAnnotation(SQLString.class) != null) {
                    SQLString sString = d.getAnnotation(
                            SQLString.class);
                    // Use field name if name not specified.
                    if (sString.name().length() < 1)
                        columnName = d.getSimpleName().toUpperCase();
                    else
                        columnName = sString.name();
                    sql += "\n    " + columnName + " VARCHAR(" +
                            sString.value() + ")" +

                            getConstraints(sString.constraints()) + ",";
                }
                if (d.getAnnotation(SQLBoolean.class) != null) {
                    SQLBoolean sBol = d.getAnnotation(
                            SQLBoolean.class);
                    // Use field name if name not specified
                    if (sBol.name().length() < 1)
                        columnName = d.getSimpleName().toUpperCase();
                    else
                        columnName = sBol.name();
                    sql += "\n    " + columnName + " BOOLEAN" +
                            getConstraints(sBol.constraints()) + ",";
                }
                if (d.getAnnotation(SQLCharacter.class) != null) {
                    SQLCharacter sChar = d.getAnnotation(
                            SQLCharacter.class);
                    // Use field name if name not specified.
                    if (sChar.name().length() < 1)
                        columnName = d.getSimpleName().toUpperCase();
                    else
                        columnName = sChar.name();
                    sql += "\n    " + columnName + " CHARACTER(" +
                            sChar.value() + ")" +
                            getConstraints(sChar.constraints()) + ",";
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
} 