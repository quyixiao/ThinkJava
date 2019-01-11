//: net/mindview/util/Directory.java
// Produce a sequence of File objects that match a
// regular expression in either a local directory,
// or by walking a directory tree.
package net.mindview.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;


/****
 *
 *
 *  *      程序设计中的一项常见的任务就是在文件中集上执行操作，这些文件要么在本地目录中，要么遍布于整个
 *  目录中，如果有一种工具能够为你产生这个文件集操作，它是非常有用的，下面的被用工具类就可以通过使用local()
 *  方法产生由本地中的文件中构成的file对象数组，或者通过使用walk()方法产生给定目录下的由整个目录树中所有的文件中构成的List<File>
 *  （File对象比文件名更有用，因为file对象包含更多的信息）这些文件是基于你提供的正则表达式而被选中的
 *
 *
 *
 *  1
 *
 */
public final class Directory {
    public static File[] local(File dir, final String regex) {
        return dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);

            public boolean accept(File dir, String name) {
                return pattern.matcher(
                        new File(name).getName()).matches();
            }
        });
    }

    public static File[] local(String path, final String regex) { // Overloaded
        return local(new File(path), regex);
    }

    // A two-tuple for returning a pair of objects:
    public static class TreeInfo implements Iterable<File> {
        public List<File> files = new ArrayList<File>();
        public List<File> dirs = new ArrayList<File>();

        // The default iterable element is the file list:
        public Iterator<File> iterator() {
            return files.iterator();
        }

        void addAll(TreeInfo other) {
            files.addAll(other.files);
            dirs.addAll(other.dirs);
        }

        public String toString() {
            return "dirs: " + PPrint.pformat(dirs) +
                    "\n\nfiles: " + PPrint.pformat(files);
        }
    }

    public static TreeInfo
    walk(String start, String regex) { // Begin recursion
        return recurseDirs(new File(start), regex);
    }

    public static TreeInfo
    walk(File start, String regex) { // Overloaded
        return recurseDirs(start, regex);
    }

    public static TreeInfo walk(File start) { // Everything
        return recurseDirs(start, ".*");
    }

    public static TreeInfo walk(String start) {
        return recurseDirs(new File(start), ".*");
    }

    static TreeInfo recurseDirs(File startDir, String regex) {
        TreeInfo result = new TreeInfo();
        for (File item : startDir.listFiles()) {
            if (item.isDirectory()) {
                result.dirs.add(item);
                result.addAll(recurseDirs(item, regex));
            } else // Regular file
                if (item.getName().matches(regex))
                    result.files.add(item);
        }
        return result;
    }

    // Simple validation test:
    public static void main(String[] args) {
        if (args.length == 0)
            System.out.println(walk("."));
        else
            for (String arg : args)
                System.out.println(walk(arg));
    }
} ///:~
