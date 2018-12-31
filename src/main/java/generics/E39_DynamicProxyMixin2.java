//: generics/E39_DynamicProxyMixin2.java
/****************** Exercise 39 *****************
 * Add a new mixin class Colored to
 * DynamicProxyMixin.java, mix it in to mixin, and
 * show that it works.
 ************************************************/
package generics;

import static net.mindview.util.Tuple.tuple;


/****
 *
 *
 * 416页
 *  向DynamicProxyMixin.java中添加一个新的混型类Colored，将其混入mixin并展示它可以工作
 *
 *
 *
 */
public class E39_DynamicProxyMixin2 {
    public static void main(String[] args) {
        Object mixin = MixinProxy.newInstance(
                tuple(new BasicImp(), Basic.class),
                tuple(new TimeStampedImp(), TimeStamped.class),
                tuple(new SerialNumberedImp(), SerialNumbered.class),
                tuple(new ColoredImp(), Colored.class));
        Basic b = (Basic) mixin;
        TimeStamped t = (TimeStamped) mixin;
        SerialNumbered s = (SerialNumbered) mixin;
        Colored11 c = (Colored11) mixin;
        b.set("Hello");
        System.out.println(b.get());
        System.out.println(t.getStamp());
        System.out.println(s.getSerialNumber());
        System.out.println(c.getColor());
    }
}