//: net/mindview/atunit/ClassNameFinder.java
package net.atunit;

import net.mindview.util.BinaryFile;
import net.mindview.util.Directory;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static net.mindview.util.Print.print;


/****
 *
 *
 * 647 页
 *
 * 虽然无法在这里介绍其中的所有细节，但每个类文件都必须遵循一定的格式，而我己经尽量用有用有意义的域名来
 * 表示这些从ByteArrayINputStream中提出来的数据片断，通过施加在输入流上的读操作，你能看出每个信息片的大小，
 * 例如，每个类文件的头32个bit总是一个，神秘的数字，hexOxcafebe, 而接下来的两个short值的版本信息，常量池
 * 包含了程序中的常量，所以这是一个可变的值，接下来的short告诉我们这个常量池有多大，然后我们才能为其尺寸创建一个
 * 元素，其长度可能是一个固定的值，也可能是一个可变的值，因此我们并不打算精确的分析类中的所有的数据，仅仅是从
 * 文件的起始一步一步地走，直到取得我们所需的信息，因此你会发现，在这个过程中，我们丢弃了大量的数据，关于类的信息，
 * 都保存在classNameTable和offfettable中，在读完了常量池之后，就找到了this_class_信息，这是offsetTable
 * 中的一个坐标，通过它能够找到一个进入，ClassNameTable的坐标，然后就可以得到我们所需的类的名字了
 *
 *
 *
 *
 * 现在，让我们回到AtUnit.java程序，process()方法现在拥有了类的名字，然后检查它是否包含 "." ,如果有就表示该类
 * 定义于一个包中，没有包的类在包中，那么，我我们就可以使用类加载器并通过Class.forName() 将其加载通过Class.forName()
 * 将其加载进来，现在，我们终于可以开始这个类进行@Unit注解分析工作了。
 *
 * 我们只需要关心三件事情，首先是@test 方法，它们将被保存在TestMethods列表中，然后检查是否具有@testObjectCreate和
 * TestObjectCleanUp 方法，从代码中可以看到，我们通过调用相应的方法来查询注解从而找到这些方法。
 * 每当找到一个@test方法，就打印出当前的类的名字，于是观察者立刻就可以知道发生了什么，接下来，开始执行测试，也就是
 * 打印出方法名，然后调用createTestObject（）如果存在一个加了@testObjectCreate注解的方法，或者调用默认的打印出类的方法名
 * ，或者调用默认的构造器，一旦创建出测试对象，就调用其上的测试方法，如果测试返回一个boolean值，就的捕获结果，如果测试方法没有
 * 返回值，那么当没有异常，就说明测试失败，那么还要统计失败的次数，并将失败的测试所属的类和方法的名字加入failedTests，以便，
 * 最后将其报告给用户。
 *
 *
 *
 *
 *
 *
 */
public class ClassNameFinder {
    public static String thisClass(byte[] classBytes) {
        Map<Integer, Integer> offsetTable =
                new HashMap<Integer, Integer>();
        Map<Integer, String> classNameTable =
                new HashMap<Integer, String>();
        try {
            DataInputStream data = new DataInputStream(
                    new ByteArrayInputStream(classBytes));
            int magic = data.readInt();  // 0xcafebabe
            int minorVersion = data.readShort();
            int majorVersion = data.readShort();
            int constant_pool_count = data.readShort();
            int[] constant_pool = new int[constant_pool_count];
            for (int i = 1; i < constant_pool_count; i++) {
                int tag = data.read();
                int tableSize;
                switch (tag) {
                    case 1: // UTF
                        int length = data.readShort();
                        char[] bytes = new char[length];
                        for (int k = 0; k < bytes.length; k++)
                            bytes[k] = (char) data.read();
                        String className = new String(bytes);
                        classNameTable.put(i, className);
                        break;
                    case 5: // LONG
                    case 6: // DOUBLE
                        data.readLong(); // discard 8 bytes
                        i++; // Special skip necessary
                        break;
                    case 7: // CLASS
                        int offset = data.readShort();
                        offsetTable.put(i, offset);
                        break;
                    case 8: // STRING
                        data.readShort(); // discard 2 bytes
                        break;
                    case 3:  // INTEGER
                    case 4:  // FLOAT
                    case 9:  // FIELD_REF
                    case 10: // METHOD_REF
                    case 11: // INTERFACE_METHOD_REF
                    case 12: // NAME_AND_TYPE
                        data.readInt(); // discard 4 bytes;
                        break;
                    default:
                        throw new RuntimeException("Bad tag " + tag);
                }
            }
            short access_flags = data.readShort();
            int this_class = data.readShort();
            int super_class = data.readShort();
            return classNameTable.get(
                    offsetTable.get(this_class)).replace('/', '.');
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Demonstration:
    public static void main(String[] args) throws Exception {
        if (args.length > 0) {
            for (String arg : args)
                print(thisClass(BinaryFile.read(new File(arg))));
        } else
            // Walk the entire tree:
            for (File klass : Directory.walk(".", ".*\\.class"))
                print(thisClass(BinaryFile.read(klass)));
    }
} ///:~
