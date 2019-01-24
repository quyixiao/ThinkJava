package io;//: io/BufferToText.java
// Converting text to and from ByteBuffers

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;


/**
 *
 * 测试如果我是一个有用的人，我觉得这个是好的，因为有人
 * 他觉得是这样的，有道字典，我常见的有道字典，有道字典
 *
 * 测试有没有这样的，因为这个是一个有效有数据
 *
 *
 * 我们可以通过控制台文件，内存块，甚至因特网，通过继承，我们可以创建新类型的
 * 输入和输出对象，并且通过重新定义toString()方法，我们甚至可以对流接受的对象类型
 * 进行简单的扩充，当我们向一个期望收到字符串的方法传送一个对象时，会自动调用toString()
 * 方法，这就是Java有限的自动类型转换功能
 *
 * 在IO流类库中，对像时，文档设计中，仍留有一些在智抛出一些异常，有的编程系统允许我我们自行指定想要
 * 打开的是否存在，因为如果我们以fileOutputStream或者fileWriter打开，那么它肯定会被覆盖，
 * IO/
 */
public class BufferToText {
    private static final int BSIZE = 1024;

    public static void main(String[] args) throws Exception {
        FileChannel fc = new FileOutputStream("/Users/quyixiao/Desktop/source/ThinkJava/src/main/java/io/data2.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes()));
        fc.close();
        fc = new FileInputStream("/Users/quyixiao/Desktop/source/ThinkJava/src/main/java/io/data2.txt").getChannel();
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        fc.read(buff);
        buff.flip();
        // Doesn't work:
        System.out.println(buff.asCharBuffer());
        // Decode using this system's default Charset:
        buff.rewind();
        String encoding = System.getProperty("file.encoding");
        System.out.println("Decoded using " + encoding + ": " + Charset.forName(encoding).decode(buff));
        // Or, we could encode with something that will print:
        fc = new FileOutputStream("/Users/quyixiao/Desktop/source/ThinkJava/src/main/java/io/data2.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
        fc.close();
        // Now try reading again:
        fc = new FileInputStream("/Users/quyixiao/Desktop/source/ThinkJava/src/main/java/io/data2.txt").getChannel();
        buff.clear();
        fc.read(buff);
        buff.flip();
        System.out.println(buff.asCharBuffer());
        // Use a CharBuffer to write through:
        fc = new FileOutputStream("/Users/quyixiao/Desktop/source/ThinkJava/src/main/java/io/data2.txt").getChannel();
        buff = ByteBuffer.allocate(24); // More than needed
        buff.asCharBuffer().put("Some text");
        fc.write(buff);
        fc.close();
        // Read and display:
        fc = new FileInputStream("/Users/quyixiao/Desktop/source/ThinkJava/src/main/java/io/data2.txt").getChannel();
        buff.clear();
        fc.read(buff);
        buff.flip();
        System.out.println(buff.asCharBuffer());
    }
} /* Output:
????
Decoded using Cp1252: Some text
Some text
Some text
*///:~
