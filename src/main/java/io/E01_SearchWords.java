//: io/E01_SearchWords.java
// {Args: java E01_SearchWords}
/****************** Exercise 1 *****************
 * Modify DirList.java (or one of its variants) so
 * that the FilenameFilter opens and reads each file
 * (using the net.mindview.util.TextFile utility) and
 * accepts the file based on whether any of the
 * trailing arguments on the command line exist in
 * that file.
 ***********************************************/
package io;

import net.mindview.util.TextFile;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/*****
 *
 *
 * 528 页
 *      修改DirList.java（或其变体之一）,以便FileNameFilter能够打开每个文件，使用net.mindviev.util.TextFile工具
 *  并检查命令行已随的参数是否存在于那个文件中，以此检查结果来决定是否的接受这个文件
 *
 *
 *
 *
 * 1
 */
public class E01_SearchWords {
    public static void main(final String[] args) {
        File path = new File(".");
        String[] list;
        if (args.length == 0)
            list = path.list();
        else
            list = path.list(new FilenameFilter() {
                private String ext = args[0].toLowerCase();

                public boolean accept(File dir, String name) {
                    // Only analyze source files with the specified
                    // extension (given as the first command line
                    // argument)
                    if (name.toLowerCase().endsWith(ext)) {
                        // Only filter upon file extension?
                        if (args.length == 1)
                            return true;
                        Set<String> words = new HashSet<String>(
                                new TextFile(new File(
                                        dir, name).getAbsolutePath(), "\\W+"));
                        for (int i = 1; i < args.length; i++)
                            if (words.contains(args[i]))
                                return true;
                    }
                    return false;
                }
            });
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem : list)
            System.out.println(dirItem);
    }
}