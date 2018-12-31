package io; //: io/E07_FileIntoList.java
/****************** Exercise 7 *****************
 * Open a text file so that you can read the file
 * one line at a time. Read each line as a String
 * and place that String object into a LinkedList.
 * Print all of the lines in the LinkedList in reverse
 * order.
 ***********************************************/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class E07_FileIntoList {
    // Throw exceptions to console:
    public static List<String>
    read(String filename) throws IOException {
        // Reading input by lines:
        BufferedReader in = new BufferedReader(
                new FileReader(filename));
        String s;
        List<String> list = new LinkedList<String>();
        while ((s = in.readLine()) != null)
            list.add(s);
        in.close();
        return list;
    }

    public static void main(String[] args)
            throws IOException {
        List<String> list = read("E07_FileIntoList.java");
        for (ListIterator<String> it =
             list.listIterator(list.size()); it.hasPrevious(); )
            System.out.println(it.previous());
    }
}