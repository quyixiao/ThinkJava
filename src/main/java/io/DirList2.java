package io;//: io/DirList2.java
// Uses anonymous inner classes.
// {Args: "D.*\.java"}

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;


/**
 * 527 页
 * <p>
 * <p>
 * <p>
 * 1
 */
public class DirList2 {


    /**
     *
     *
     *
     *
     * 注意：传向filter() 的参数必须是final 的，这在匿名的内部类中是必需的，这样它才能使用来自该类
     * 范围之外的对象
     * 这个类设计有所改进，因为现在FilenameFilter 类紧密地和DirList2 绑定在一起，然而，我们可以
     * 进一步修改该方法，定义一个作为list() 参数的匿名内部类，这样一来程序会变得更小
     * 这是一个问题， 因这这是一个问题的，这
     * @param regex
     * @return
     */
    public static FilenameFilter filter(final String regex) {
        // Creation of anonymous inner class:
        return new FilenameFilter() {

            private Pattern pattern = Pattern.compile(regex);

            public boolean accept(File dir, String name) {

                System.out.println("====================" + name);

                return pattern.matcher(name).matches();


            }
        }; // End of anonymous inner class
    }



    public static void main(String[] args) {
        File path = new File(".");
        String[] list;
        list = path.list(filter("target"));
/*
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem : list) {
            System.out.println("++++++++++++++++" + dirItem);
        }*/
    }
}



/* Output:
DirectoryDemo.java
DirList.java
DirList2.java
DirList3.java
*///:~
