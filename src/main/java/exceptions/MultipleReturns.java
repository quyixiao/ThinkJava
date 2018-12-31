package exceptions;//: exceptions/MultipleReturns.java

import static net.mindview.util.Print.print;


/*****
 *
 *
 * 267 页
 *
 *
 *      因为finally子句总是返回执行的，所以在一个方法中，可以从多个点返回，并且，
 * 可以保证重要的清理工作仍旧会执行
 *
 *
 * 1
 */
public class MultipleReturns {
    public static void f(int i) {
        print("Initialization that requires cleanup");
        try {
            print("Point 1");
            if (i == 1) return;
            print("Point 2");
            if (i == 2) return;
            print("Point 3");
            if (i == 3) return;
            print("End");
            return;
        } finally {
            print("Performing cleanup");
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 4; i++) {
            f(i);
        }
    }
}





/* Output:
Initialization that requires cleanup
Point 1
Performing cleanup
Initialization that requires cleanup
Point 1
Point 2
Performing cleanup
Initialization that requires cleanup
Point 1
Point 2
Point 3
Performing cleanup
Initialization that requires cleanup
Point 1
Point 2
Point 3
End
Performing cleanup
*///:~
