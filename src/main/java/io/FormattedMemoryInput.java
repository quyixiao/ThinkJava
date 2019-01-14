package io;//: io/FormattedMemoryInput.java

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;


/**
 * 541 页
 * 要读取格式化数据，可能使用datainputStream ,它是一个面向字节的I/O类，不是面向字符的，因
 * 此我们必须使用Inputstream类，而不是Reader类，当然，我们可以用inputStream 字节的形式读取任何的数据，例如一个文件，不过
 * 在这里使用的是字符串
 * <p>
 * <p>
 * <p>
 * <p>
 * 1
 */
public class FormattedMemoryInput {
    public static void main(String[] args)
            throws IOException {
        try {
            DataInputStream in = new DataInputStream(
                    new ByteArrayInputStream(
                            BufferedInputFile.read(
                                    "/Users/quyixiao/Desktop/source/ThinkJava/src/main/java/io/FormattedMemoryInput.java").getBytes()));
            while (true)
                System.out.print((char) in.readByte());
        } catch (EOFException e) {
            System.err.println("End of stream");
        }
    }
} /* (Execute to see output) *///:~
