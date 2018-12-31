//: initialization/E20_VarargMain.java
// {Args: These, are, some, strings}
/****************** Exercise 20 *****************
 * Create a main() that uses varargs instead
 * of the ordinary main() syntax. Print all the
 * elements in the resulting args array. Test it
 * with various numbers of command-line
 * arguments.
 ************************************************/
package initialization;

/***
 * 105页
 *  创建一个使用可变能数列表而不是普通的main（）语法的main()，打印所产生的
 *  args数组所有的元素，并用各种不同数量的命令行参数来测试它
 *
 */
public class E20_VarargMain {
    public static void main(String... args) {
        E19_VarargStringArray.printStrings(args);
    }
}