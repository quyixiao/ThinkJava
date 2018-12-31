package exceptions;//: exceptions/Cleanup.java
// Guaranteeing proper cleanup of a resource.


/****
 *
 * 273页
 *      除了内存的清理调用，即使能确定它将被调用，也不知道什么时候调用，这也是java的缺陷，除了内存
 * 的清理之外，所有的清理都不会自动发生，所以必须告诉客户端程序员，这是他们的责任
 *
 *
 * 1
 */
public class Cleanup {
    public static void main(String[] args) {
        try {
            InputFile in = new InputFile("/Users/quyixiao/Desktop/source/ThinkJava/src/main/java/exceptions/Cleanup.java");

            /**
             * 在创建需要清理的对象之后，立即进入一个try-finally语句块
             *
             */
            try {
                String s;
                int i = 1;
                while ((s = in.getLine()) != null) {
                    System.out.println(s); // Perform line-by-line proce// ssing here...
                }
            } catch (Exception e) {
                System.out.println("Caught Exception in main");
                e.printStackTrace(System.out);
            } finally {
                System.out.println("");
                System.out.println("finall");
                in.dispose();
            }
        } catch (Exception e) {
            System.out.println("InputFile construction failed");
        }
    }
}