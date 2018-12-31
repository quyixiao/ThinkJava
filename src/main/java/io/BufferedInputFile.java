package io;//: io/BufferedInputFile.java

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * 字符串sb 用来累积文件的全部内容，包括必须添加的换行符，因为readLine() 已将它们删掉
 * 最后调用close() 来关闭文件
 *
 *
 *
 */
public class BufferedInputFile {
    // Throw exceptions to console:
    public static String
    read(String filename) throws IOException {
        // Reading input by lines:
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = in.readLine()) != null)
            sb.append(s + "\n");
        in.close();
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.print(read("/Users/quyixiao/Desktop/source/ThinkJava/src/main/java/io/BufferedInputFile.java"));
    }
} /* (Execute to see output) *///:~



