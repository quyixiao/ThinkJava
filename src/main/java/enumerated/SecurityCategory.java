package enumerated;//: enumerated/SecurityCategory.java
// More succinct subcategorization of enums.

import net.mindview.util.Enums;


/***
 *
 *
 * 599 页
 *
 *
 * Security 接口的作用是将其所包含的enum组合成一个公共的类型，这一点是有必要的，然后securityCategory
 * 才能将Security 中的enum作为其构造器的参数使用，以起到组织的效果
 *
 *
 *
 *
 *
 * 1
 *
 */
enum SecurityCategory {
    STOCK(Security.Stock.class), BOND(Security.Bond.class);
    Security[] values;

    SecurityCategory(Class<? extends Security> kind) {
        values = kind.getEnumConstants();
    }

    interface Security {
        enum Stock implements Security {SHORT, LONG, MARGIN}

        enum Bond implements Security {MUNICIPAL, JUNK}
    }

    public Security randomSelection() {
        return Enums.random(values);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            SecurityCategory category = Enums.random(SecurityCategory.class);
            System.out.println(category + ": " + category.randomSelection());
        }
    }
}




/* Output:
BOND: MUNICIPAL
BOND: MUNICIPAL
STOCK: MARGIN
STOCK: MARGIN
BOND: JUNK
STOCK: SHORT
STOCK: LONG
STOCK: LONG
BOND: MUNICIPAL
BOND: JUNK
*///:~
