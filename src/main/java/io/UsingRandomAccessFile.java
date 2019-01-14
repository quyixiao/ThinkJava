package io;//: io/UsingRandomAccessFile.java

import java.io.IOException;
import java.io.RandomAccessFile;


/**
 * 544 页
 * writeDouble() 将double类型的数字存储到流中，并用相应的readDouble() 恢复它，对于其他的数据
 * 类型，也有类似方法用于读写，但是为了保证所有的读方法都能够正常工作，我们必须知道流中的数据项所在的
 * 确切的位置，因为极有可能将保存的double数据作为一个简单的字节序列，char 或其他类型读入，因此，我们必须
 * ：要么为文件中数据采用固定的格式，要么将额外的的信息保存到文件中，以便是更容易的存储和读取复杂的数据
 * 结构的方式
 * <p>
 * <p>
 * <p>
 * 可以自行选择的是第二个构造器参数，我们可指定以 只读 方式或 读写的方式打开一个程序，它可以存储后获取
 * RandomAccessFile 类能够提供的所有不同的只读，方式或读写方式打开一个RandomAccessFile文件
 * <p>
 * <p>
 * 管道流
 * <p>
 * PipedInputStream ,PipedOutputStream ，PipedReader,PipedWriter 在本章只是简单的提到，但是这并不
 * 表明它们没有什么用处，它们的价值只有在我们开始理解多线程之后，才会显现，因为管道流用于任务之间的通信，
 * 这些在第 21 章会用一个示例进行讲述
 * <p>
 * <p>
 * <p>
 * 1
 */
public class UsingRandomAccessFile {
    static String file = "rtest.dat";

    static void display() throws IOException {
        RandomAccessFile rf = new RandomAccessFile(file, "r");
        for (int i = 0; i < 7; i++)
            System.out.println("Value " + i + ": " + rf.readDouble());
        System.out.println(rf.readUTF());
        rf.close();
    }

    public static void main(String[] args) throws IOException {
        RandomAccessFile rf = new RandomAccessFile(file, "rw");
        for (int i = 0; i < 7; i++)
            rf.writeDouble(i * 1.414);
        rf.writeUTF("The end of the file");
        rf.close();
        display();
        rf = new RandomAccessFile(file, "rw");
        rf.seek(5 * 8);
        rf.writeDouble(47.0001);
        rf.close();
        display();
    }
}



/* Output:
Value 0: 0.0
Value 1: 1.414
Value 2: 2.828
Value 3: 4.242
Value 4: 5.656
Value 5: 7.069999999999999
Value 6: 8.484
The end of the file
Value 0: 0.0
Value 1: 1.414
Value 2: 2.828
Value 3: 4.242
Value 4: 5.656
Value 5: 47.0001
Value 6: 8.484
The end of the file
*///:~
