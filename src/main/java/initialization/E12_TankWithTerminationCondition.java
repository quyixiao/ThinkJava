package initialization;


/****
 * 89页
 * 编写一个名为Tank类，此类的状态是可以是满的，或 空的，其终结条件是：
 * 对象被清理时必须牌空的状态，请编写finalize()以检验终结条件是否成立，在main()中测试
 * Tank可能发生几种使用方式
 *
 */
public class E12_TankWithTerminationCondition {
    public static void main(String args[]) {
        new Tank().empty();
        new Tank();
        // Don't empty the second one
        System.gc(); // Force finalization?
        System.runFinalization();
    }
} 