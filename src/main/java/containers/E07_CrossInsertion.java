//: containers/E07_CrossInsertion.java
/****************** Exercise 7 *****************
 * Create both an ArrayList and a LinkedList, and
 * fill each using the Countries.names() generator.
 * Print each list using an ordinary Iterator, then
 * insert one list into the other by using a
 * ListIterator, inserting at every other location.
 * Now perform the insertion starting at the end of
 * the first list and moving backward.
 ***********************************************/
package containers;

import net.mindview.util.Countries;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import static net.mindview.util.Print.print;


/*****
 *
 * 477页
 *
 *
 *
 * 创建一个ArrayList和lInkedList，用Contries.names()生成器来填充每个容器，
 * 用普通的Iterator打印每个列表，然后用ListIterator按隔一个位置插入一个对象的方式把一个
 * 表插入到另一个表中，现在，从第1个表的末尾开始，向前移动执行插入操作
 *
 *
 *
 * 1
 */
public class E07_CrossInsertion {
    public static void main(String args[]) {
        ArrayList<String> al = new ArrayList<String>(Countries.names(10));
        LinkedList<String> ll = new LinkedList<String>(Countries.names(20).subList(10, 20));
        for (Iterator<String> it = al.iterator(); it.hasNext(); )
            print(it.next());
        print("**************************************************");
        for (Iterator<String> it = ll.iterator(); it.hasNext(); )
            print(it.next());
        print("***********************************************");
        int alindex = 0;
        for (ListIterator<String> lit2 = ll.listIterator(); lit2.hasNext(); ) {
            al.add(alindex, lit2.next());
            alindex += 2;
        }
        print("al = " + al);
        print(alindex);
        print("********");
        alindex = 0;
        // Start at the end:
        for (ListIterator<String> lit2 = ll.listIterator(ll.size()); lit2.hasPrevious(); ) {
            al.add(alindex, lit2.previous());
            alindex += 2;
        }
        print("al = " + al);
        print(alindex);
    }
}