//: generics/E30_AutoboxingUnboxingTest.java
/****************** Exercise 30 *****************
 * Create a Holder for each of the primitive
 * wrapper types, and show that autoboxing and
 * autounboxing works for the set() and get()
 * methods of each instance.
 ************************************************/
package generics;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import static net.mindview.util.Print.print;


/*****
 *
 *
 * 401页
 *      为每一种基本类型的包装器类型都创建一个Holder，并展示自动包装和自动拆
 *  包机制对每个实例的set()和get()方法都作用
 *
 *
 *
 *  1
 *
 */
public class E30_AutoboxingUnboxingTest {
    public static void main(String[] args) {
        Holder<Integer> hi = new Holder<Integer>();
        hi.set(1);
        int i = hi.get();
        print(i == 1);
        System.out.println("111111111111111111111111111111111111111");
        Holder<Byte> hb = new Holder<Byte>();
        hb.set((byte) 1);
        byte b = hb.get();
        print(b == 1);
        System.out.println("22222222222222222222222222222222222");
        Holder<Short> hs = new Holder<Short>();
        hs.set((short) 1);
        short s = hs.get();
        print(s == 1);
        System.out.println("33333333333333333333333333333333333");
        Holder<Long> hl = new Holder<Long>();
        hl.set(1L);
        long l = hl.get();
        print(l == 1);
        System.out.println("4444444444444444444444444444444");
        Holder<Float> hf = new Holder<Float>();
        hf.set(1.0F);
        float f = hf.get();
        print(f == 1.0F);
        Holder<Double> hd = new Holder<Double>();
        hd.set(1.0);
        double d = hd.get();
        print(d == 1.0);
        Holder<Character> hc = new Holder<Character>();
        hc.set('A');
        char c = hc.get();
        print(c == 'A');
        Holder<Boolean> hbool = new Holder<Boolean>();
        hbool.set(true);
        boolean bool = hbool.get();
        print(bool);
    }
}