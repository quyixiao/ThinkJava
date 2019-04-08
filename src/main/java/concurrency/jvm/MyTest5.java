package concurrency.jvm;

import net.mindview.util.Stack;

import java.util.Random;


/***
 *
 *
 *
 * 当一个接口被初始化时，并不要求其你接口都完成了初始化ac
 *
 *
 * 只有真正使用到父接口的时候，如 引用接口中所定义的常量的时， 都会被初始化
 *
 *
 *
 * 加载：就是把二进制形式的java类型读入到java虚拟机中
 * 验证：为类变量分配内存，设置默认值，但是在到达初始化之前，类变量都没有初始化为真正的初始值
 * 解析：解析过程就是类型的常量池中寻找类，接口，字段和方法符号的的引用，把这些符号引用替换成直接引用的过程
 *
 * 初始化：为类变量赋予正确的初始值
 *
 * 类实例化：
 * 为新的对象分配内存
 * 为实例变量赋默认值
 * 为实例变量赋正确的初始值
 * java编译器为它编译的每一个类都至少生成一个实例初始化方法，在java的文件中，这个实例初始化方法被称为
 * init 针对源代码中每一个类的构造方法，java编译器都产生一个init方法
 *
 * 类的加载的最终产品是位于内存中的Class对象
 * Class对象是封装了类的方法区内的数据结构，并且向java程序程序员提供了访问方法区的内部数据结构
 *
 * 类的加载
 *  有两种类型的类加载器
 *      java虚拟机自带的加载器
 *          根类加载器（Bootstrap）
 *          扩展加载器（Extension）
 *          系统加载（应用）加载器（System）
 *    用户自定义的类加载器
 *          java.lang.ClassLoader的子类
 *          用户可以定制类加载方式
 *  JVM 规范允许类加载器预料某个类将要被使用时就预先加载它，如果在预先加载
 *  的过程中遇到了.class文件缺失或存在错误，类加载器必须在程序首次主动使用该类时才
 *  报告错误，
 *  如果这个类一直没有被程序主动使用，那么这个类加载器就不会报告这个错误
 *
 *  类被加载后，就进入连接阶段，连接就是将已经读入到内存中的类二进制数据合并到
 *  虚拟机的运行环境中去
 *
 * 类的验证的内容
 *  类的文件的结构检查
 *  语义检查
 *  字节码验证
 *  二进制兼容性的验证
 *
 *  在你准备阶段，java虚拟机为类的静态变量分配内存，并设置默认值的初始值，例如对simple类，在准备阶段，将为int类型的
 *  静态变量a分配4个字节的内存空间，并且赋予默认值，为long类型的变量b分配8的字节的内存空间，并且赋予的默认值0
 *
 *  在初始化阶段，java虚拟机执行类的初始化语句，为类的
 *
 *  静态变量的声明语句，以及静态代码都被看做类的初始化语句，java虚拟机会按照初始化语句在类文件中的先后顺序来依次执行它们
 *  ，例如当以下Sample类被初始化后，它的静态变量a的取值为4
 *
 class Simple{


 static int a = 1;


 static {
 a = 2;
 }
 static {
 a = 4;
 }



 }


类的初始化步骤
    假如这个类还没有被加载和连接，那就先进行加载和连接
    假如类存在在直接父类，并且这个父类还没有被初始化，那就先初始化直接父类
    假如类中存在初始化语句，那就依次执行这些初始化语句


 类的初始化时机
    主动使用（七种，重要）
    创建类的实例
    访问某个类或接口的静态变量，或者对该静态变量赋值
    调用类的静态方法
    反射（如 Class.forName("com.test.Test")）
    裙初始化一个类的子类
    java虚拟机启动时被标明为启动类的类（Java Test）
 类的初始化时机
    JDK.1.7开始提供的动态语言支持
    java.lang.invoke.MethodHandle实例解析结果REF_getStatic，REF_putStatic
 REF_invokeStatic句柄对应的类没有初始化则初始化
    除了上述的七种情形，其他的使用java类的方式都被看作是被动使用，不会导致类的初始化
    类的初始化时机
    当Java虚拟机初始化一个类时，要求它所有的父类都已经被初始化，但是这条规则不适用于接口
    在初始化一个类时，并不会先初始化它所有实现的接口
    在初始化一个接口时，并不会先初始化它的接口
    因此，一个父接口不会因为它的子接口或者实现类的初始化而初始化，只有当程序首次使用接口静态变量时，都会导致接口的
 初始化
    只有当程序访问静态变量或静态方法确实在当前或当前接口中定义时，才可以认为是对类或接口的主动使用
    调用ClassLoader类的LoadClass方法加载一个类，并不是对类的主动使用，不会导到类的初始化
    类加载器用来把类加载到Java虚拟机中，类的加载过程采用父亲委托机制，这种机制能更好的保证java平台的安全性
    ，在些委托机制中，除了Java虚拟机自带的根类加载器以外，其余的类加载器都有一个父加载器，当java程序请求加载器load1
    加载Samele类时，loader1首先委托自己的父加载器去加载Sample类，若父加载器能加载，则由父加载器完成加载任务，
 否则才由加载器loader1本身加载Sample类
    java虚拟机自带了以下几种加载器
    根类加载器：该加载器没有父加载器，它负责加载虚拟机的核心类库，如java.lang.*等，例如从例程10-4(sample.java中可以看出)
 ，java.lang.object就是由根类加载器加载的，根类加载器从系统属性sun.boot.class.path所指定的目录中加载类库，根类加载器从系统属性
 sun.boot.class.path所指定的目录加载类库，根类加载器实现依赖于底层的操作系统，属于虚拟机的实现的一部分，它并没胡继承java.lang.ClassLoader类
 扩展（Extension）类加载器，它是父加载器为根类加载器，它从java.ext.dirs系统属性所指定的目录中加载类库，或者从JDK的安装目录中的jre\lib\ext子目录中
 扩展目录下加载类库，如果把用户创建的JAR文件放在这个目录下，也会自动由扩展加载器加载，扩展类加载器是纯java类，是java.lang.ClassLoader类的子类
 系统类加载器，也称为应用类的加载器，它的父类加载为扩展类加载器，它从变量classpath或者系统属性java.classpath所指定的目录中加载类，它是用户
 自定义的类加载器的默认父类器，系统类加载器是纯java类，是java.lang.Classloader类的子类
    根类加载器->扩展类加载器->系统类加载器->用户自定义加载器
    当java虚拟机初始化一个类，要求它的所有父类加载器已经初始化，但是这条规则并不适用于所有的接口
    在初始化一个类时，并不会先初始化它所有的实现的接口
    在初始化一个的接口时，并不会先初始化它的父类接口
    因此，一个父接口并不会因为它的子接口或才实现类的初始化而初始化，只有当程序首次使用特定接口静态变量时，才会收导致该接

    在父亲委托机制中，各个加载器按照你儿子关系形成了树形结构，除了根类加载器之外，其余的类加载器都有一个父加载器


    Bootstrap ClassLoader -> Load JRE\lib\rt.jar或者-Xbootclasspath选项指定的jar包
    Extension ClassLoader -> Load JRE\lib\ext\*.jar或Djava.ext.dirs指定目录下的jar包
    App ClassLoader       -> Load CLASSPATH或Djava.class.path所指定的目录下的类和jar包
    Custom ClassLoader    -> 通过java.lang.ClassLoader的子类 自定义加载class

    Bootstrap ClassLoader /启动类加载器
        $JAVA_HOME中的jre/lib/rt.jar里所有的s,由C++实现，不是ClassLoader子类
    Extension ClassLoader/扩展类加载器，
        负责加载java平台中的扩展功能一些jar包，包括$JAVA_HOME中的jre/lib/*.jar 或-Djava.ext.dirs指定目录下的jar包
    App ClassLoader/系统类加载器
        负责加载classpath中指定的jar包及目录中的class
    若有一个类加载器能够成功加载Test类，那么这个类加载器被称为定义类加载器，所有能成功返回Class对象引用的类加载器
    包括定义类加载器，都被称为初始类加载器
























 *
 *
 *
 *
 *
 */
public class MyTest5 {


    public static void main(String[] args) {


        System.out.println(MyChild5.b);
    }



}






interface MyParent5{

    public static int a = new Random().nextInt(3);

}


interface MyChild5 extends  MyParent5{
    public static int b = new Random().nextInt(4);
}