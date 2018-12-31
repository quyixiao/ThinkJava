package io;//: io/FileOutputShortcut.java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;


/**
 * PrintWriter 中添加了一个辅助的构造器，使得你不必在每次希望创建文本文件并向其中写入时，都去执行所有的装饰工作，
 * 下面是用这样的快捷的方式重写了BasicFileoutput.java
 *
 * 你仍旧是在进行缓存，只是不必自己去实现，遗憾的是，其他的写入任务都没有快捷的方式，因此典型的I/O 仍旧包含大量的
 * 冗余的文本，但是，本书使用的在本章稍后进行定义的TextFile 工具简化了这些常见的任务
 *
 *
 *
 *
 *
 */
public class FileOutputShortcut {
    static String file = "/Users/quyixiao/Desktop/source/ThinkJava/src/main/java/io/FileOutputShortcut.out";

    public static void main(String[] args)
            throws IOException {
        BufferedReader in = new BufferedReader(
                new StringReader(
                        BufferedInputFile.read("/Users/quyixiao/Desktop/source/ThinkJava/src/main/java/io/FileOutputShortcut.java")));
        // Here's the shortcut:
        PrintWriter out = new PrintWriter(file);
        int lineCount = 1;
        String s;
        while ((s = in.readLine()) != null)
            out.println(lineCount++ + ": " + s);
        out.close();
        // Show the stored file:
        System.out.println(BufferedInputFile.read(file));
    }
} /* (Execute to see output) *///:~
