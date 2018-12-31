//: initialization/E21_PaperCurrencyTypesEnum.java
/****************** Exercise 21 ****************
 * Create an enum of the six lowest denominations
 * of paper currency. Loop through the values()
 * and print each value and its ordinal().
 ***********************************************/
package initialization;

/***
 *
 * 107页
 *  创建一个enum，它包含纸币中的最小面值的6种类型，通过values()循环并打印每个值及其ordinal()
 *
 */
enum PaperCurrencyTypes {
    ONE, TWO, FIVE, TEN, TWENTY, FIFTY
}

public class E21_PaperCurrencyTypesEnum {
    public static void main(String args[]) {
        for (PaperCurrencyTypes s : PaperCurrencyTypes.values())
            System.out.println(s + ", ordinal " + s.ordinal());
    }
}