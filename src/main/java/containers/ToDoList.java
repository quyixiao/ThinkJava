package containers;//: containers/ToDoList.java
// A more complex use of PriorityQueue.

import java.util.PriorityQueue;

/**
 * 在第11章曾经给出过优先级队列的一个简单介绍，其中更有趣的问题是to-do列表
 * ，该列表中每个对象都包含一个字符串和一个主要的以及次要的优先级值，该列表
 * 的排序也是通过实现Compareable而进行控制的
 */
class ToDoList extends PriorityQueue<ToDoList.ToDoItem> {
    static class ToDoItem implements Comparable<ToDoItem> {
        private char primary;
        private int secondary;
        private String item;

        public ToDoItem(String td, char pri, int sec) {
            primary = pri;
            secondary = sec;
            item = td;
        }

        public int compareTo(ToDoItem arg) {
            if (primary > arg.primary)
                return +1;
            if (primary == arg.primary)
                if (secondary > arg.secondary)
                    return +1;
                else if (secondary == arg.secondary)
                    return 0;
            return -1;
        }

        public String toString() {
            return Character.toString(primary) +
                    secondary + ": " + item;
        }
    }

    public void add(String td, char pri, int sec) {
        super.add(new ToDoItem(td, pri, sec));
    }

    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        toDoList.add("Empty trash", 'C', 4);
        toDoList.add("Feed dog", 'A', 2);
        toDoList.add("Feed bird", 'B', 7);
        toDoList.add("Mow lawn", 'C', 3);
        toDoList.add("Water lawn", 'A', 1);
        toDoList.add("Feed cat", 'B', 1);
        while (!toDoList.isEmpty())
            System.out.println(toDoList.remove());
    }
} /* Output:
A1: Water lawn
A2: Feed dog
B1: Feed cat
B7: Feed bird
C3: Mow lawn
C4: Empty trash
*///:~
