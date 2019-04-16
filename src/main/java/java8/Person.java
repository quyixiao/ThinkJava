package java8;


/***
 * 定义或者生成数据，有一些类是在生成的时候生成出来的，生成类定义的相关数据
 * 读取这些文档的时候，类加载器要
 *
 * 每一个class对象都有一个classLoader的引用
 *
 *  为了loadclass方法来去加载其他的类
 * 数组类的Class对象是由运行时java上位机创建的
 * 对于数组来说，其类型是由JVM在运行期动态生成的，表示【Lcom.shengsiyuang.jvm.classLoader.Myparent4】
 * 这种形式，动态生成类型，其父类型就是Object
 * 对于数组来说，JavaDoc经常将构成数组元素Component，实际上就是将数组降低一个维度后的类型
 * 助记符
 *      anewarray:表示创建一个引用类型的（如类，接口，数组）数组，并将其引用值在压入栈顶
 *      newarray:表示创建一个指定的原始类型（如 int,float ,char 等）数组，并将其引用值在压入栈顶
 */
public class Person {


}
