package io;//: io/TestEOF.java
// Testing for end of file while reading a byte at a time.

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;


/**
 *
 * 542 页
 * 必须为byteArrayInputStream 提供字节数组，为了产生该数组string包含了一个可以实现此
 * 项工作的getBytes()方法，所产生的ByteArrayInputStream 是一个合适的传递给DataInputStream的inputstream
 * 如果我们从DataInputStream 用readByte() 一次一个字节地读取字符，那么任何字节的值都
 * 是合法的结果，因此返回值不能用来检测输入是否结束，相反，我们可以使用avaliable() 方法查看还有
 * 多少可供存取的字符，下面这个例子演示了怎样的一次一个字节的读取文件
 *
 *
 *
 * 注意，available() 的工作方式会随着所读取的媒介不同而不同，字面意思就是在没有阻塞的情况下的所能读取的字节数
 * ，对于文件，这意味的着整个文件，但是对于不同类型的流，可能就不是这样子的了，因此要谨慎使用
 *
 *
 *
 *
 *
 */
public class TestEOF {
    public static void main(String[] args)
            throws IOException {
        DataInputStream in = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream("/Users/quyixiao/Desktop/source/ThinkJava/src/main/java/io/TestEOF.java")));
        while (in.available() != 0)
            System.out.print((char) in.readByte());
    }
} /* (Execute to see output) *///:~
