//: initialization/E22_PaperCurrencyTypesEnum2.java
/****************** Exercise 22 ****************
 * Write a switch statement for the enum in
 * Exercise 21. For each case, output a
 * description of that particular currency.
 ***********************************************/
package initialization;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;


/***
 *
 *  107页
 *
 *  在前面的例子中，为enum写一个switch语句，对于每一个case，输出该特定的货币的描述
 *
 *
 *
 *
 */
public class E22_PaperCurrencyTypesEnum2 {
    static void describe(PaperCurrencyTypes pct) {
        printnb(pct + " has a portrait of ");
        switch (pct) {
            case ONE:
                print("George Washington");
                break;
            case TWO:
                print("Thomas Jefferson");
                break;
            case FIVE:
                print("Abraham Lincoln");
                break;
            case TEN:
                print("Alexander Hamilton");
                break;
            case TWENTY:
                print("Andrew Jackson");
                break;
            case FIFTY:
                print("U.S. Grant");
                break;
        }
    }

    public static void main(String args[]) {
        for (PaperCurrencyTypes s : PaperCurrencyTypes.values())
            describe(s);
    }
}