package containers;//: containers/References.java
// Demonstrates Reference objects

import java.lang.ref.*;
import java.util.LinkedList;


/***
 *
 *
 * 519页
 *
 * java.land.ref 类库包含了一组类，这些类为垃圾回收提供了更大的灵活性，当存在可能会耗尽
 * 内存的大对象的时候，这些类显得特别有用，有三个继承自抽象类Reference 的类，sortReference
 *  ，WeakReference 和 PhantomReference ， 当垃圾回收器正在考察的对象只能通过某个Reference  对象才"可获得" 时，上述这些
 *  不同的派生类为垃圾回收器提供了不同级别的间接性指示
 *
 *
 *
 *  对象是可获得的，是指此对象可在程序中的其处找到，这意味着你在栈中有一个普通的引用，而它正指向此对象的，也可能是你的引用
 *  指向某个对象，而那个对象含有另一个引用指向正在讨论的的对象，也可能有更多的中间链接，如果一个对象是"可获得的"，那么你的
 *  程序将无法使用它，所以将其回收是安全的。
 *
 *
 *
 *
 * 以Reference 对象作为你和普通的引用之间的媒介，另外，一定不能有普通的引用指向那个对象，这样就能达到上述目的，
 * 普通引用是指没有Referece对象包装过的引用，如果垃圾回收器发现某个对象通过普通引用是可获得的，该对象就不会被
 * 释放掉
 *
 *
 * SortReferece ，WeakReferece 和 PhantomReferece 由强到弱的排列，对应的不同级别的，可获得性，Sorftrefence 用以
 * 实现内存敏感的高速缓存，Weak refence 是为实现 "规范映射" 而设计的，它不妨碍垃圾回收器回收映射的键 （或 值），Phantomrefernce 用
 * 以调度回收前的清理工作，它比Java终止更加的灵活
 *
 *
 * 使用SorftRefernce 和 WeakReferce 时 ，可以选择是否要将它们放入 ReferceQueue （用作） 回收清理工作，的
 * 工具， 而PhantomReferce 只能ReferceceQueue ，下面是一个简单的示例
 *
 *
 *
 * 容器的类中的一种特殊的Map , 即WeakHashMap ,它被用秋保存WeakReferce ，它使得规范映射更易于使用，在这种映射中
 * ，每个值只保存一份来实例存储空间，当程序需要那个值的时候，便在映射中查询现有的对象，然后使用它，而不是重新创建，
 * 映射可将值作为其初始化中的一部分，不过通常是在需要的时候才生成值。
 *
 *
 *
 *
 * 这是一种节约存储空间的技术，因为WeakHashMap  允许垃圾回收器自动清理键和值，所以它显得十分便利，对于向WeakHashMap
 * 添加键和值的操作，则没有什么特殊的要求，映射会自动的使用WeakRefence  包装它们，允许清理元素的触发条件是，不再需要
 * 此键了
 *
 *
 *
 *
 * 1
 *
 *
 */
class VeryBig {
    private static final int SIZE = 10000;
    private long[] la = new long[SIZE];
    private String ident;

    public VeryBig(String id) {
        ident = id;
    }

    public String toString() {
        return ident;
    }

    protected void finalize() {
        System.out.println("Finalizing " + ident);
    }
}

public class References {
    private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<VeryBig>();

    public static void checkQueue() {
        Reference<? extends VeryBig> inq = rq.poll();
        if (inq != null)
            System.out.println("In queue: " + inq.get());
    }

    public static void main(String[] args) {
        int size = 10;
        // Or, choose size via the command line:
        if (args.length > 0)
            size = new Integer(args[0]);
        LinkedList<SoftReference<VeryBig>> sa = new LinkedList<SoftReference<VeryBig>>();
        for (int i = 0; i < size; i++) {
            sa.add(new SoftReference<VeryBig>(new VeryBig("Soft " + i), rq));
            System.out.println("Just created: " + sa.getLast());
            checkQueue();
        }
        LinkedList<WeakReference<VeryBig>> wa = new LinkedList<WeakReference<VeryBig>>();
        for (int i = 0; i < size; i++) {
            wa.add(new WeakReference<VeryBig>(new VeryBig("Weak " + i), rq));
            System.out.println("Just created: " + wa.getLast());
            checkQueue();
        }
        SoftReference<VeryBig> s =
                new SoftReference<VeryBig>(new VeryBig("Soft"));
        WeakReference<VeryBig> w =
                new WeakReference<VeryBig>(new VeryBig("Weak"));
        System.gc();
        LinkedList<PhantomReference<VeryBig>> pa =
                new LinkedList<PhantomReference<VeryBig>>();
        for (int i = 0; i < size; i++) {
            pa.add(new PhantomReference<VeryBig>(
                    new VeryBig("Phantom " + i), rq));
            System.out.println("Just created: " + pa.getLast());
            checkQueue();
        }
    }
} /* (Execute to see output) *///:~
