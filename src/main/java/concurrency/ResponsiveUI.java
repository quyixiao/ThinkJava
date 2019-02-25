package concurrency;//: concurrency/ResponsiveUI.java
// User interface responsiveness.
// {RunByHand}


/****
 *
 *
 * 671页
 *
 * 创建有响应的用户界面
 *  UnresponsiveUi是一个无限的while循环时执行的运算，显然程序不可能到达读取控制台输入的那一行
 *  编译器被欺骗了，相信while的条件使得程序能到达读取控制台输入的那一行，如果反建立UnresponsiveUI这
 *  一行的注释解除掉再运行程序，那么要终止的它的话，就只能杀死这个进程了，
 *      要想让程序有响应，就得把计算程序放在run()方法中，这样它就能让处理给别的程序。
 *
 *  当你按下回车的时候，可能看到计算确实在后台程序运行，同时还在等待用户输入，
 *
 *  线程组
 *      线程线持有一个线程集合，线程组的价值可以引用Joshua Bloch的话来总结，他在sun时是软件软件
 *   架构师，订正并极大的改善了JDK 1.2中的java集合类库
 *    最好把线程组看成是一次不成功的尝试，你只要忽略它就好了
 *    如果你花费大量的时间和精力试图发现线程组中的价值，就像我一样，那么你可能会惊异为什么没有来自sun的关于这个
 *    主题的官方声明，多年以来，相同的问题对于java发生的其他的变化询问过无数遍，诺贝尔经济学奖得主josph Stiglitz的
 *    生活哲学可以用来解释这个问题，它被称为承诺升级理论
 *
 *    继续错误的代价由别人来承担，而承认错误的代价由自己承担
 *
 *
 *
 *
 *
 *
 *
 */
class UnresponsiveUI {
    private volatile double d = 1;

    public UnresponsiveUI() throws Exception {
        while (d > 0)
            d = d + (Math.PI + Math.E) / d;
        System.in.read(); // Never gets here
    }
}

public class ResponsiveUI extends Thread {
    private static volatile double d = 1;

    public ResponsiveUI() {
        setDaemon(true);
        start();
    }

    public void run() {
        while (true) {
            d = d + (Math.PI + Math.E) / d;
        }
    }

    public static void main(String[] args) throws Exception {
       // new UnresponsiveUI(); // Must kill this process
        new ResponsiveUI();
        System.in.read();
        System.out.println(d); // Shows progress
    }
} ///:~
