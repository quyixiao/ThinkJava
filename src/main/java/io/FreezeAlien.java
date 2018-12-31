package io;//: io/FreezeAlien.java
// Create a serialized output file.

import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;


/**
 * 575 页
 *
 * 创建一个和序列化一个Alien对象的文件也位于相同的目录下
 *
 *
 *
 */
public class FreezeAlien {
    public static void main(String[] args) throws Exception {
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream("/Users/quyixiao/Desktop/source/ThinkJava/src/main/java/io/X.file"));
        Alien quellek = new Alien();
        out.writeObject(quellek);
    }
} ///:~
