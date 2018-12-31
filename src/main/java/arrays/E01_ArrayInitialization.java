package arrays;


/*****
 *
 *
 *
 *
 * 436页
 *
 *
 *      创建一个受BerylliumSphere数组作为参数方法，并动态地创建参数去调用这
 *  这个方法，证明在配合中普通的聚集数组初始化不能奏效，去发现总结在哪些情况下，普通的聚集
 *  初始化可以起作用，而以在哪些情况下，动态聚集初始化显得多余
 *
 *
 *
 * 1
 *
 */
public class E01_ArrayInitialization {
    static void hide(BerylliumSphere[] s) {
        System.out.println("Hiding " + s.length + " sphere(s)");
    }

    public static void main(String[] args) {
        // Dynamic aggregate initialization:
        hide(new BerylliumSphere[]{new BerylliumSphere(), new BerylliumSphere()});
        // The following line produces a compilation error.
        //! hide({ new BerylliumSphere() });
        // Aggregate initialization:
        BerylliumSphere[] d = {new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere()};
        hide(d);
        // Dynamic aggregate initialization is redundant
        // in the next case:
        BerylliumSphere[] a = new BerylliumSphere[]{new BerylliumSphere(), new BerylliumSphere()};
        hide(a);
    }
}