package io;//: io/StoringAndRecoveringData.java

import java.io.*;


/**
 * 543 页
 *
 * PrintWriter 可以对数据进行格式化，以便人们的阅读，但是为了输出可供另一个流 恢复的数据，
 * 我们需要用DataOutputStream 写入数据，并用DataInputStream恢复数据，并用DataInputStream恢复数据
 * 当然这些流可以是任何形式的，但在下面的示例中使用的是一个文件，并且对于读和写都进行了缓冲处理，注意
 * DataOutputStream和DataInputStream 是面向字节的，因此要使用InputStream 和outputStream
 *
 *
 *
 */
public class StoringAndRecoveringData {
    public static void main(String[] args)
            throws IOException {
        DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("Data.txt")));
        out.writeDouble(3.14159);
        out.writeUTF("That was pi");
        out.writeDouble(1.41413);
        out.writeUTF("Square root of 2");
        out.close();
        DataInputStream in = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream("Data.txt")));



        System.out.println(in.readDouble());



        // Only readUTF() will recover the
        // Java-UTF String properly:
        System.out.println(in.readUTF());
        System.out.println(in.readDouble());
        System.out.println(in.readUTF());
    }
} /* Output:
3.14159
That was pi
1.41413
Square root of 2
*///:~
