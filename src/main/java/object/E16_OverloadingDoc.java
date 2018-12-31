package object;


/*****
 *  37 页
 *
 *   找到第5章中的Overloading.java示例，并为它加入javadoc文档，然后用javadoc提取此注释文档，并产生一个HTML文件
 *   ，最后，能完Web浏览器查看结果
 *
 */
public class E16_OverloadingDoc {
    /**
     * Creates <b>Tree</b> objects and exercises the two
     * different <code>info()</code> methods.
     */
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Tree t = new Tree(i);
            t.info();
            t.info("overloaded method");
        }
        // Overloaded constructor:
        new Tree();
    }
} 