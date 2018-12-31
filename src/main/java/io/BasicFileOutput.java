package io;//: io/BasicFileOutput.java

import java.io.*;


/**
 * FileWriter 对象可能向文件写入数据，首先，创建一个与指定文件连接的FileWriter ,实际上，我们通常会用
 * BufferdWriter 将其包装起来用以缓冲输出，尝试移除此包装来感受对性能的影响，缓冲往往能显著地增加I/O 的操作
 * 性能，在本例中，为了提供格式化机制，它被装饰成了PrintWriter ，按照这种方式创建的数据文件可作为普通文本读取
 *
 *
 *
 * 542 页
 *
 * 当文本行被写入文件时，行号就会增加，注意并未用到LinenUmberInputStream ，因为这个类没有多大的帮助
 * ，所以我们没有必要用它，人本例中，记录自己的行号是多么的容易。
 * 一旦读完输入数据流，readline 会返回null, 我们可能看到要为out显式的调用close() ,如果我们不为所有的输出
 * 文件调用close() ，就会发现，缓冲区的内容不会被刷新清空，那么它们也就不完整。
 *
 *
 *
 *
 *
 *
 */
public class BasicFileOutput {
    static String file = "/Users/quyixiao/Desktop/source/ThinkJava/src/main/java/io/BasicFileOutput.out";

    public static void main(String[] args)
            throws IOException {
        BufferedReader in = new BufferedReader(
                new StringReader(
                        BufferedInputFile.read("/Users/quyixiao/Desktop/source/ThinkJava/src/main/java/io/BasicFileOutput.java")));
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(file)));
        int lineCount = 1;
        String s;
        while ((s = in.readLine()) != null)
            out.println(lineCount++ + ": " + s);
        out.close();
        // Show the stored file:
        System.out.println(BufferedInputFile.read(file));
    }
} /* (Execute to see output) *///:~
