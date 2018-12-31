package typeinfo;//: typeinfo/E07_CommandLoad.java
// {Args: typeinfo.Gum typeinfo.Cookie}
/****************** Exercise 7 ******************
 * Modify SweetShop.java so that each type of
 * object creation is controlled by a
 * command-line argument. That is, if your
 * command line is "java SweetShop Candy," then
 * only the Candy object is created. Notice how
 * you can control which Class objects are loaded
 * via the command-line argument.
 ***********************************************/


import static net.mindview.util.Print.print;


/******
 *
 *
 * 318页
 *      修改前一个练习，让这个方法使用Class.getDeclaredFields()来打印一个类中的域
 *  的相关信息。
 *
 *  1
 */
class Candy1 {
    static {
        print("Loading Candy");
    }
}

class Gum1 {
    static {
        print("Loading Gum");
    }
}

class Cookie1 {
    static {
        print("Loading Cookie");
    }
}

public class E07_CommandLoad {
    public static void main(String[] args) throws Exception {
        for (String arg : args)
            Class.forName(arg);
    }
}