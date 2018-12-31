package control;//: control/LabeledWhile.java
// While loops with "labeled break" and "labeled continue."

import static net.mindview.util.Print.print;


/****
 * 73页
 *
 *
 * 1
 *
 *
 * 1.一般的continue会退回最内层循环的开头顶部，并继续执行
 * 2.带标签的continue会到达标签的位置，并重新进入紧接在那个标签后面的循环
 * 3.一般的break会中中断并跳出当前循环
 * 4.带标签的break会中断并标签所指的循环
 *
 * */
public class LabeledWhile {
    public static void main(String[] args) {
        int i = 0;
        outer:
        while (true) {
            print("Outer while loop");
            while (true) {
                i++;
                print("i = " + i);
                if (i == 1) {
                    print("continue");
                    continue;
                }
                if (i == 3) {
                    print("continue outer");
                    continue outer;
                }
                if (i == 5) {
                    print("break");
                    break;
                }
                if (i == 7) {
                    print("break outer");
                    break outer;
                }
            }
        }
    }
}



/* Output:
Outer while loop
i = 1
continue
i = 2
i = 3
continue outer
Outer while loop
i = 4
i = 5
break
Outer while loop
i = 6
i = 7
break outer
*///:~
