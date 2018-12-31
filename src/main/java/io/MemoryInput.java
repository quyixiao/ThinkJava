package io;//: io/MemoryInput.java

import java.io.IOException;
import java.io.StringReader;


/**
 * 注意read() 是以int形式返回下一字节，因此必须转换为char 才能正确打印
 *
 *
 *
 */
public class MemoryInput {
    public static void main(String[] args) throws IOException {
        StringReader in = new StringReader(
                BufferedInputFile.read("/Users/quyixiao/Desktop/source/ThinkJava/src/main/java/io/MemoryInput.java"));
        int c;
        while ((c = in.read()) != -1)
            System.out.print((char) c);
    }
} /* (Execute to see output) *///:~
