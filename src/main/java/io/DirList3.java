package io;//: io/DirList3.java
// Building the anonymous inner class "in-place."
// {Args: "D.*\.java"}

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;


/**
 * 527
 * <p>
 * <p>
 * <p>
 * 1
 *
 *
 *
 * 既然匿名内部类直接使用args[0],那传递给main()方法的参数现在就是final的
 *
 *
 */
public class DirList3 {
    public static void main(final String[] args) {
        File path = new File(".");
        String[] list;
        if (args.length == 0) {
            list = path.list();
        } else {
            list = path.list(new FilenameFilter() {
                private Pattern pattern = Pattern.compile("txt");

                public boolean accept(File dir, String name) {
                    return pattern.matcher(name).matches();
                }
            });
        }
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem : list) {
            System.out.println(dirItem);
        }
    }
}



/* Output:
DirectoryDemo.java
DirList.java
DirList2.java
DirList3.java
*///:~
