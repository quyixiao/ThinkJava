package io;//: io/DirList.java
// Display a directory listing using regular expressions.
// {Args: "D.*\.java"}

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;


/**
 * 526 页
 * <p>
 * DirFiller 这个类存在的唯一原因就是将accept() 方法，创建这个类的目的在于把accept()方法
 * 提供给list()使用，使list() 可以回调accept() ,进而以决定哪些文件包含在列表中，因此，这种结
 * 构也常称为回调accept() ，这是一种策略模式的例子，因为list() 实现了基本的功能，而且按照 FilenameFileter
 * 的形式提供了这个策略，以便完善list() 要提供服务时所需要的算法，因为list() 接受 FilenameFileter 对象
 * 作为参数，这意味着我们可以传递实现了FilenameFileter接口的任何类的对象，用以选择（甚至在运行时）
 * list() 方法的行为方式，策略的目的就是提供了代码的行为的灵活性
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 1
 */
public class DirList {
    public static void main(String[] args) {
        File path = new File(".");
        String[] list;
        if (args.length == 0) {
            list = path.list();
        } else {
            list = path.list(new DirFilter(args[0]));
        }
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem : list) {
            System.out.println("------" + dirItem);
        }
    }
}

class DirFilter implements FilenameFilter {
    private Pattern pattern;

    public DirFilter(String regex) {
        pattern = Pattern.compile(regex);
    }

    public boolean accept(File dir, String name) {


        return pattern.matcher(name).matches();
    }
}





/* Output:
DirectoryDemo.java
DirList.java
DirList2.java
DirList3.java
*///:~
