package generics;//: generics/LostInformation.java

import java.util.*;


/***
 * 373
 *
 *
 *
 *
 * 在泛型代码内部，无法获得任何有关泛型参数的类型信息
 *
 *          Java泛型用来擦实现的，这意味当你使用泛型时，任何具体的类型信息都被擦除，
 * 你唯一知道的就是你在使用一个对象，因此List<String></String>和List<Interger></Interger>运行时
 * 事实是是相同的类型，这两种形式都被擦除成它们原生的类型，即List,理解擦除以及应该如何处理它，是你在学习
 * Java泛型时面临界的最大障碍，这也是我们在本节将本探讨的内容
 *
 *
 *
 *
 * 1
 */
class Frob {
}

class Fnorkle {
}

class Quark<Q> {
}

class Particle<POSITION, MOMENTUM> {
}

public class LostInformation {
    public static void main(String[] args) {
        List<Frob> list = new ArrayList<Frob>();
        Map<Frob, Fnorkle> map = new HashMap<Frob, Fnorkle>();
        Quark<Fnorkle> quark = new Quark<Fnorkle>();
        Particle<Long, Double> p = new Particle<Long, Double>();


        System.out.println(Arrays.toString(list.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(map.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(quark.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(p.getClass().getTypeParameters()));
    }
} /* Output:
[E]
[K, V]
[Q]
[POSITION, MOMENTUM]
*///:~
