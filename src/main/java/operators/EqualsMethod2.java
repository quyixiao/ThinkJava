package operators;//: operators/EqualsMethod2.java
// Default equals() does not compare contents.

import javax.annotation.Resource;

class Value {
    int i;


    /***
     * 重写equals方法
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Value) {
            return this.i == ((Value) obj).i;
        }
        return false;
    }

}


/*****
 *
 *
 *
 *
 * 45页
 *  1  测试写equal方法和不写equal方法的区别
 */
public class EqualsMethod2 {
    public static void main(String[] args) {
        Value v1 = new Value();
        Value v2 = new Value();
        v1.i = v2.i = 100;
        System.out.println(v1.equals(v2));
    }
}



/* Output:
false
*///:~
