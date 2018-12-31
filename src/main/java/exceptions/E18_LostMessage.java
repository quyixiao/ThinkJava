package exceptions;


/******
 *
 *
 * 269页
 *  为LostMessage.java添加第二层异常丢失，以便用第三个异常来替代HoHumException异常
 *
 */
public class E18_LostMessage {
    public static void main(String[] args) {
        try {
            LostMessage2 lm = new LostMessage2();
            try {
                try {
                    lm.f();
                } finally {
                    lm.dispose();
                }
            } finally {
                lm.cleanup();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}