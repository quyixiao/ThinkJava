package io;//: io/MakeDirectories.java
// Demonstrates the use of the File class to
// create directories and manipulate files.
// {Args: MakeDirectoriesTest}

import java.io.File;


/***
 *
 * 534 页
 *
 *
 * File类不仅仅只代表存在的文件或目录，也可以用File 对象来创建样的的目录或尚不存在的
 * 整个目录路径，我们还可以查看文件的的特性（如：大小，最后修改日期，读写）检查某个File
 * 对象是一个文件还是一个目录，并可以删除文件，下面的示例展示了File类的一些其他的方法
 *
 *
 *
 *
 *
 * 在fileData中，可以看到了多种不同的文件特征查询方法来显示文件或目录路径的信息，main（）
 * 方法首先调用的是renameTo() ,用来把一个文件重命名（或移动）到由参数所指示的另一个完全的
 * 不同的的新路径，也就是另一个File对象，下面，这同样的适用于任意的文件目录。
 *
 *
 *
 * 编程语言中的I/O类库中常使用流这个概念，它代表任何有能力产出数据的数据源对象或者是有能力
 * 接收数据的接收端对象，流 屏蔽了实际的I/O设备中处理数据的细节
 *
 *
 *
 *
 *  java 类库中的I/O 类分成输入和输出两个部分，可以以JDK 文档里的类的层次结构中可以看到
 *  通过继承，任何自Inputstream 或 reader 派生而来的类都含有名为read()的基本用法，用于
 *  读取单个字节数组，同样，任何自OutputStream 或 Writer 派生而来的read() 的类都含有名为
 *  write() 的基本方法，用于写单个字节或数组，但是，我们通常不会用这些方法，它们之所以存在是因为
 *  别的类可以使用它们，以便提供期望的的功能（这是装饰器设计的模式，你将在本书中看到它）
 *  实际上，java中，流 类库让人迷惑的的原因就在于，创建单一结果的流，却需要创建多个对象
 *
 *
 * inputStream 的作用是用来表示那些不同数据源产生的输入的类，如表 所示 这些数据源包括
 *
 *
 * 1)字节数组
 * 2） String 对象
 * 3）文件
 * 4）管道 ，工作方式实际管道相似 即 从一端输入 ，从另一端输出
 * 5）一个由其他种类的流组成的序列，以便我们可以收集合并到一起
 * 6）其他的数据源，如internet连接等
 * 每一个数据源都有相应的InputtStream 子类 ，另外FilerInputStream 也属于一种 InputStream 为
 * 装饰器 ，类提供基类，其中，装饰器类可以可能把属性或用用的接口与输入的流连在一起，我们稍后再讨论它
 *
 *
 *
 *
 *  类
 *  ByteArrayInputStream   允许你将内存的缓冲区当作 inputStream 使用              缓冲区，字节将从中取出，作为一种数据源，将其与FileerInputStream 对象相连以提供有用的接口
 *  StringBufferInputStream 将String 置换成InputStream  字符串 ，底层实现使用StringBuffer 作为一种数据源，将其与FileInputStream 对象相连以提供有用的接口
 *  FileInputStream   用于从文件中读取信息  字符串，表示文件名，文件或FileDescriptor 对象 ，作为一种数据源，将其与FilterInputStream 对象相连以提供有用的接口
 *  SequenceInputStream  将两个或多个inputStream对象置换成单一的InputStream   两个InputStream 对象或一个容纳InputStream 对象的容器Enumeration,作为一种数据源，将其与 FileInputStream 对象相连，以提供有用的接口
 *  FilterInputStream 抽象类，作为 "装饰器接口" 其中，装饰器，为其他的InputStream 类提供有用的功能
 *
 *
 *
 * outputStream 类型
 *  另外 ，FilterOutPutStream 为装装饰器 类提供了一个基类，类把属性或者有用的接口与输出流连接了起来，这些稍后会讨论
 *  ByteArrayOutputStream 在内存中创建缓冲区，所有关往 "流" 的数据都要放置在此缓冲区                           缓冲区初始尺寸，用于指定数据目的 将其与FilterOutputStream 对象相连以提供有用的接口
 *  FileOutStream  用于将信息写至文件                        字符串，表示文件名，文件或FileDescripter对象，指定数据的目地，将其与FileterOutputStream 对象相连以提供有用的接口
 *  FileoutputStream  抽象类，作为 装饰器接口，其中，装饰器，为其他的OutputStream  提供有用的功能，
 *
 *
 *
 *
 *  FileInputStream 和FileterOutputStream 是用来提供装饰器类接口以控制特定输入流和输出流的两个类，它们的名字并不是很直观，FilterInputSteram 和FileOutPutStream 分别自
 *  I/O 类库的基类，inputStream 和OutputStream 派生而来的，这两个类是装饰器的必要条件 ，以便能为所有的正在被修饰的对象提供通用的接口
 *
 *
 *
 *
 *  不同的基本数据类型数据及String对象（所有的方法都以"read" 开头，例如ReadByte() ,readFloat() 等等） 搭配相应的DataOutputStream ,我们就可以通过数据流，将基本的类型的数据从一个
 *  地方迁移到另一个地方，是由表中的那些类决定
 *
 *
 *
 *  DataInputStream  与DataOutputStream 的基本使用，因此我们可以按照可移值方式从流基本的数据类型（int,char,long 等）        InputStream 包含用于读取基本的类型数据的全部接口
 *
 *  BufferedInputStream 使用它可以防止每次读取时都得进行实际的写操作，代表 "缓冲区"                         InputStream 可以指定缓冲区大小，可选的，本质上不提供接口，只不过是向进程中
 *  添加缓冲区必需的，与接口对象搭配
 *
 *  LineNumberInputStream  跟踪输入流中的行号，可调用getlineNumber()和setlineNumber(int)   InputStream 仅增加了行号，可能需要和接口对象搭配使用。
 *
 *
 *
 *  PushbackInputStream 具有 能弹出一个字节缓冲区，因此可以将读取最后一个字符回退
 *
 *
 *  PrintStream 内有两个重要的方法，print(),println() ，对它们进行了重载，以便可印出各种数据类型，print(）和println) 之间的差异是，后者在操作完成后添加一个换行符
 *
 *
 *
 *  PrintStream 可能会有些问题，因为它捕捉了所有的IoException ,因此，我们必胡须使用checkError() 自行测试错误状态，如果出现错误它返回true, 另外，printStram 也未
 *  完全国际化，不能以平台无关的方式处理的行动作，这些问题在printWriter 中得到了解决，这在后面讲述
 *
 *  BufferdOutPutStream 是一个修改过的OutputStream  ，它对数据流使用缓冲技术，因此当每次向流写入时，不必每次都进行实际的物理写动作，所以在进行输出时，我们可能更
 *  经常的是使用它，FilterOutStream 的类型有功能
 *
 *
 *
 *
 *
 *  DataOutputStream  与DataInputStream 的搭配使用，因此可以按照可移植的方式向流中写入基本的类型数据（int,char,long等） outputStream 包含用于写入基本类型数据的全部接口
 *  PrintStream  用于产生格式化输出，其中DataoutputStream 处理数据的存储，PrintStram处理显示，   OutputStream 可能用boolean 值指示是否每次换行清空缓冲区，应该是对OutputStream 对象的final封装
 *  可能会经常使用它。
 *
 *
 *
 *  bufferedoutputStream 使用它以避免每次发送数据时都要进行实际的写操作，代表使用缓冲区，可能调用flush()清空缓冲区 outputStream，可以指定缓冲区大小，可选的，本质上并不提供接口，只不过是向进
 *  进程中添加缓冲区所必需的，与接口对象搭配
 *
 *
 *
 *
 *
 *
 * 数据的来源和去处
 * 几乎所有的原始的Java I/O 流类都有相应的Reader和Writer类来提供天然的unicode操作，然而在某些场合，面向字节的inputStream 和outputstream 才是正确的解决方案，特别的是，类java.zip 类库是面向
 * 字节的InputStream 和 OutputStream 才是正确的解决方案，最明智的做法就是尽量尝试使用Reader和Writer ，一旦程序编译，我们不得不使用面向字节的类库。
 *
 * 下面是表展示了继承层次中，信息的来源和去处（即数据物理上来自哪里及去向哪里）
 *
 * InputStream                      Reader
 * OutputStream                     适配器 InputStreamReader ,Writer
 * FileInputStream          适配器：OutputStreamWriter
 * FileInputStream                  fileReader
 * FileoutputStream                 fileWriter
 * StringBufferInputStream          Stringreader
 * ByteArrayInputStream             CharArrayReader
 * ByteArrayOutputStream            CharArrayWriter
 * PipedInputStream                 PipedReader
 * PipedOutputStream                PipedWriter
 *
 *
 *
 * 大体上，我们会发现，这两个不相同的继承层次结构中的接口即使不能说完全相同，但是非常相似
 *
 *
 * 对于InputStream 和OutputStream 来说，我们会使用FilterInputstream 和FileterOutputStream 的装饰器子类来修改，流 以满足特殊的需要，Reader 和 Writer 的类
 * 继承层次结构没用相同的思想，但是并不完全相同
 *
 *
 * 在下表中，相对于前一表格来说，左右之间的对应关系的近似程度更加粗略一些，造成这种差别的原因是因为类的组织形式不同，尽管BufferOutputStream是FileerOutpuitStream 的子类
 * 但是bufferedWriter并不是FilterWriter的子类 ，（尽管FileterWriter是抽象类,没有任何的子类，把它放在那里也只是把它作为一个占位符，或仅仅让我们不会所在的地方产生给疑惑，
 * 然而这些的接口却十分相似）
 *
 *
 *
 *
 *
 *
 * FilterInputStream                FileerReader
 * FilterOutputStream               FileWriter 抽象类，没有子类
 * BufferdInputstream               BufferedReader(也有readLine())
 * bufferdOutputStream              BufferdWriter
 * DataInputStram                   使用DatainputStram （除了当需要使用ReadLine()时以外，这时应该使用BufferedReader）
 PrintkStram            PrintWriter
 lineNumberInputStram               LinenumberReader
 *
 * StringTokeniezer                 lineNumberReader
 * PushbackInputStream                    PUshbackReader
 * 为了更容易地过渡到使用PrintWriter ,它提供了一个既能接受Writer对象，又能接受任何OutputStream 对象的构造器，PrintkWriter的格式化接口实际上与PrintStream相同
 * 在javaSEt  5 中添加了PrintWriter构造器，以简化在将输出写入时的文件创建过程，你马上就看到它
 *
 * 有一种PrintWriter 构造器还有一个选项，就是自动行清空选项，如果构造器设置此选项，则在每个Println()执行，便会自动清空
 * RandomAccessFile 适用于由大小已知的记录组成的文件，所以我们可以使用seek() 将记录从一处转移到另一处，然后读取或者修改记录，文件中记录的大小不一定相同，只要我们能够
 * 确定那些记录在多大以及它们在文件中的位置即可
 *
 *
 *
 * 最初，我们可能难以相信RandomAccessFile 不是InputStream 或者Outputstream继承层次结构中的一部分，除了实现了DAtaInput和DataOutput接口，（DataInputStream 和
 * DataoutputStream ，intpustream 和outputstream 类中已有任何功能，它是一个完全独立的类，从头开始编写其所行为，因为我们可以在一个文件内向前和向后移动，在任何情况
 * 下，在任何情况下，他都是自我独立的，直接从Object 派生出来的）
 *
 * 从本质上来说，RandomAccesFiler的工作方式类似于把DatainputStream 和DataOutStream 组合起来使用，还添加了一些方法，其中方法getFilePointer()用于想找当前的所处的
 * 文件位置，seek() 用于在文件内移至新的位置，length()用于判断文件的最大尺寸，另外，其构造器还需要第二个参数，用来指示我们只是随机读，还是即读以写，它并不是支持只写文件
 * ，这表明RandomAccessFile，若是DataInputStream 继承而来的也可能会运行得很好。
 *
 *
 *
 *
 * 缓冲输入文件
 * 如果想要打开一个文件用于字符输入，可以使用String 或 File 文件名的FileInputStream ,为了提高速度，我们希望那个文件进行缓冲，那么，为了提高速度，我们希望对那个文件进行
 * 缓冲，那么将所产生的引用传给一个BufferedReader构造器，当readLine() 将返回 null时，你就达到了文件的末尾
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * 1
 *
 *
 *
 */
public class MakeDirectories {
    private static void usage() {
        System.err.println(
                "Usage:MakeDirectories path1 ...\n" +
                        "Creates each path\n" +
                        "Usage:MakeDirectories -d path1 ...\n" +
                        "Deletes each path\n" +
                        "Usage:MakeDirectories -r path1 path2\n" +
                        "Renames from path1 to path2");
        System.exit(1);
    }

    private static void fileData(File f) {
        System.out.println(
                "Absolute path: " + f.getAbsolutePath() +
                        "\n Can read: " + f.canRead() +
                        "\n Can write: " + f.canWrite() +
                        "\n getName: " + f.getName() +
                        "\n getParent: " + f.getParent() +
                        "\n getPath: " + f.getPath() +
                        "\n length: " + f.length() +
                        "\n lastModified: " + f.lastModified());
        if (f.isFile()) {
            System.out.println("It's a file");
        } else if (f.isDirectory()) {
            System.out.println("It's a directory");
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) usage();
        if (args[0].equals("-r")) {
            if (args.length != 3) usage();
            File
                    old = new File(args[1]),
                    rname = new File(args[2]);
            old.renameTo(rname);
            fileData(old);
            fileData(rname);
            return; // Exit main
        }
        int count = 0;
        boolean del = false;
        if (args[0].equals("-d")) {
            count++;
            del = true;
        }
        count--;
        while (++count < args.length) {
            File f = new File(args[count]);
            if (f.exists()) {
                System.out.println(f + " exists");
                if (del) {
                    System.out.println("deleting..." + f);
                    f.delete();
                }
            } else { // Doesn't exist
                if (!del) {
                    f.mkdirs();
                    System.out.println("created " + f);
                }
            }
            fileData(f);
        }
    }
}








/* Output: (80% match)
created MakeDirectoriesTest
Absolute path: d:\aaa-TIJ4\code\io\MakeDirectoriesTest
 Can read: true
 Can write: true
 getName: MakeDirectoriesTest
 getParent: null
 getPath: MakeDirectoriesTest
 length: 0
 lastModified: 1101690308831
It's a directory
*///:~
