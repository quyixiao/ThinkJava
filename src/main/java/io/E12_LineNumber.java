//: io/E12_LineNumber.java
// {Args: E12_LineNumber.java E12_LineNumber.out}
/****************** Exercise 12 *****************
 * Modify Exercise 8 to also open a text file so
 * you can write text into it. Write the lines in the
 * LinkedList, along with line numbers (do not
 * attempt to use the “LineNumber” classes), out
 * to the file.
 ***********************************************/
package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.ListIterator;

/***
 *
 *
 * 541
 *
 * 注意read()是以int形式返回下一个字节，因此，必须类型转换为char才能正确打印
 *
 * 1
 */
public class E12_LineNumber {
    public static void main(String[] args) throws java.io.IOException {

        List<String> list = E07_FileIntoList.read("/Users/quyixiao/Desktop/source/ThinkJava/src/main/java/io/E12_LineNumber.java");
        PrintWriter out =
                new PrintWriter(
                        new BufferedWriter(
                                new FileWriter("/Users/quyixiao/Desktop/source/ThinkJava/src/main/java/io/E12_LineNumber1.java")));
        // We need to count backwards...
        int line = list.size();
        for (ListIterator<String> it =
             list.listIterator(list.size()); it.hasPrevious(); )
            out.println(line-- + ": " + it.previous());
        out.close();
    }
}