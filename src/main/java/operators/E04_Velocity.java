package operators;


/******
 * 43 页
 *  编写一个计算速度的程序，它所使用的距离和时间都是常量
 *
 *  1
 *
 */
public class E04_Velocity {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println(
                    "Usage: java E04_Velocity distance time");
            System.exit(1);
        }
        float distance = Float.parseFloat(args[0]);
        float time = Float.parseFloat(args[1]);
        System.out.print("Velocity = ");
        System.out.print(distance / time);
        // Change the next line if you want to use a different
        // unit for 'distance'
        System.out.println(" m/s");
    }
}