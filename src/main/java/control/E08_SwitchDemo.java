//: control/E08_SwitchDemo.java
/****************** Exercise 8 *****************
 * Create a switch statement inside a for loop
 * that tries each case and prints a message. Put
 * a break after each case and test it, then see
 * what happens when you remove the breaks.
 ***********************************************/
package control;


/*****
 *  75 页
 *
 *  写一个switch开关语句，为每个case打印一个消息，然后把这个switch放进for循环
 *  来测试每个case,先让每个case后面都有break，测试一下会怎样，然后把break删了，看看会怎样
 *
 *
 * 1
 */
public class E08_SwitchDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 7; i++) {
            switch (i) {
                case 1:
                    System.out.println("case 1");
                    break;
                case 2:
                    System.out.println("case 2");
                    break;
                case 3:
                    System.out.println("case 3");
                    break;
                case 4:
                    System.out.println("case 4");
                    break;
                case 5:
                    System.out.println("case 5");
                    break;
                default:
                    System.out.println("default");
            }
        }
    }
}