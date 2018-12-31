package access.connection2;


/****
 *
 * 123页
 * 效访示例Lunch.java的形式，创建一个名为ConnectionManager的类，该类管理
 * 一个元素为Connection对象的固定数组，客户端程序员不能直接创建Connection对象，而
 * 只能通过ConnectionManager中的某个static方法来获取它们，当ConnectionManager之中不再有对象时
 * ，它会返回null引用，在main()之中检测这些类。
 *
 */
public class E08_ConnectionManager2 {
    public static void main(String args[]) {
        Connection[] ca = new Connection[10];
        // Use up all the connections
        for (int i = 0; i < 10; i++)
            ca[i] = ConnectionManager.getConnection();
        // Should produce "null" since there are no
        // more connections:
        System.out.println(ConnectionManager.getConnection());
        // Return connections, then get them out:
        for (int i = 0; i < 5; i++) {
            ca[i].checkIn();
            Connection c = ConnectionManager.getConnection();
            System.out.println(c);
            c.doSomething();
            c.checkIn();
        }
    }
}