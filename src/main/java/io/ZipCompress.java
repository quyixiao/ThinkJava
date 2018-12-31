package io;//: io/ZipCompress.java
// Uses Zip compression to compress any
// number of files given on the command line.
// {Args: ZipCompress.java}

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

import static net.mindview.util.Print.print;


/**
 * 对于每一个要加入的压缩档案的文件，都必须调用putNextEntty() ,并将其传递给一个ZipEntry对象，ZipEntry 对象包含了一个
 * 功能很广泛的接口，允许你获取和设置Zip文件文件内该特定项上所有的可利用的的数据，名字，压缩的和未压缩的文件大小，日期，CRC
 * 校验和，额外字段数据，注释，压缩方法以及它是否是一个目录入口等等，然而，尽管Zip格式提供了设置密码的方法，但Java的Zip类库
 * 并不提供这方面的支持，虽然CheckedinputStream 和 CheckedOutPutStream 都支持Aller32 和CRC32 两种型的校验和，但是ZipEntry
 *  类只有一个支持CRC的接口，虽然这是一个底层格式的限制，但却限制了人们快速的使用速度更快的Adler32
 *
 *
 *  为了能够解压缩文件，ZipInputStream 提供了一个更简单的方法getNextEntry()方法返回了一下ZipEntry(如果存在的话)，解压文件
 *  有一个更简单的方法，利用zipFile对象读取文件，该对象，但是可以保存entrieds()方法用来向ZipEntries返回一个Enumeration
 *
 *
 *
 *   570页
 *
 *  jar
 *  c 创建一个新的或空的压缩文档
 *  t 列出目录表
 *  x 解压所有的文件
 *  x file 解压该文件
 *  f 意指 ， 我打算指定一个文件名，jar 假设所有的输入都是标准输入，或者在创建一个文件时，输出对象也是标准输出。
 *
 *  m 表示一个参数将是用户自建的清单文件的名字
 *  v 产生详细的输出，描述jar 所做的工作
 *  O 只储存文件，不压缩文件（用来创建一个可放在类路径的的jar文件）
 *  M 不自动创建文件清单
 *
 * 如果压缩文件压缩到众多的某个子目录，那么该子目录会被自动的添加到jar文件夹中，且包括该目录的所有的子目录，
 * 路径信息也会被保留
 *
 *
 *以下是一些调用jar的典型方法，下面的命令创建一个名为myJarFile.jar的文件，该文件包含了当前的目录的所有
 * 的类文件，以及自动产生的清单文件
 *
        jar cf MyJarFile.jar *.class

 下面的命令是与前类似的，但添加一个名为myMainfestFile.mf 的用户自建清单文件
 *   jar cmf MyJarFile.jar myMainfestFile.mf *.class
 *
 *   下面的命令会产生myJarFile.jar内的所有的文件的一个目录
 *   jar tf myJarFile.jar
 *
 *   下面命令添加"v" 详尽的标志，可以提供有关myjarFile.jar 中的文件的更详细的信息，
 *     jar tvf myJarFile.jar
 *
 *     假定audio ,class 和 image 是子目录，下面的命令是将所有的子目录合并到文件myApp.jar 中，其中
 * 也包括了"v"标志，当jar程序运行时，该标志可以提供更详细的信息：
 *  jar cvf myApp.jar audio class image
 *
 *  如果用0 选项创建一个Jar文件，那么该文件就可以放入类路径的变量（CLASSPATH）中
 *  CLASSPATH="lib1.jar,lib2.jar,lib3.jar"
 *
 *  然后Java就可以在lib1.jar和lib2.jar和lib3.jar中搜索目标文件了。
 *
 *
 *  jar 工具的功能没有zip工具强大，例如，不能够对己有的Jar文件进行添加或更新文件的操作，它只能从头创建一个
 *  JAR文件，同时，也不能将文件移动至一个Jar文件，并在移动后将它们删除，然而，在一种平台上创建一的Jar文件
 *  可以被在其它任何平台的上的jar 工具透明的阅读（这）
 *
 *
 *    java的对象序列化将那些实现了Serializable 接口的对象换成一个字节序列，并能够在以后将这个字符序列完全
 *    恢复为原来的对象，这一过程甚至可以通过网络进行，这意味着序列化机制能自动弥补不同操作系统的之间的差距
 *
 *
 *
 *
 *
 *
 *
 */
public class ZipCompress {
    public static void main(String[] args) throws IOException {
        FileOutputStream f = new FileOutputStream("test.zip");
        CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
        ZipOutputStream zos = new ZipOutputStream(csum);
        BufferedOutputStream out = new BufferedOutputStream(zos);
        zos.setComment("A test of Java Zipping");
        // No corresponding getComment(), though.
        for (String arg : args) {
            print("Writing file " + arg);
            BufferedReader in = new BufferedReader(new FileReader(arg));
            zos.putNextEntry(new ZipEntry(arg));
            int c;
            while ((c = in.read()) != -1)
                out.write(c);
            in.close();
            out.flush();
        }
        out.close();
        // Checksum valid only after the file has been closed!
        print("Checksum: " + csum.getChecksum().getValue());
        // Now extract the files:
        print("Reading file");



        FileInputStream fi = new FileInputStream("test.zip");
        CheckedInputStream csumi = new CheckedInputStream(fi, new Adler32());


        ZipInputStream in2 = new ZipInputStream(csumi);
        BufferedInputStream bis = new BufferedInputStream(in2);
        ZipEntry ze;
        while ((ze = in2.getNextEntry()) != null) {
            print("Reading file " + ze);
            int x;
            while ((x = bis.read()) != -1)
                System.out.write(x);
        }
        if (args.length == 1)
            print("Checksum: " + csumi.getChecksum().getValue());
        bis.close();
        // Alternative way to open and read Zip files:
        ZipFile zf = new ZipFile("test.zip");
        Enumeration e = zf.entries();
        while (e.hasMoreElements()) {
            ZipEntry ze2 = (ZipEntry) e.nextElement();
            print("File: " + ze2);
            // ... and extract the data as before
        }
    /* if(args.length == 1) */
    }
} /* (Execute to see output) *///:~
