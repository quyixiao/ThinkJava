package exceptions;//: exceptions/LostMessage.java
// How an exception can be lost.


/***
 *    268页
 *
 *          遗憾的是，java的异常实现也是有瑕疵的，异常作为程序的出错的标志，决不应该被忽略，但是还是
 *    有可能被轻易忽略，用某些特殊的方式使用finally子句，就会发生这种情况。
 *
 *
 */
class VeryImportantException extends Exception {
    public String toString() {
        return "A very important exception!";
    }
}

class HoHumException extends Exception {
    public String toString() {
        return "A trivial exception";
    }
}

public class LostMessage {
    void f() throws VeryImportantException {
        throw new VeryImportantException();
    }

    void dispose() throws HoHumException {
        throw new HoHumException();
    }

    public static void main(String[] args) {
        try {
            LostMessage lm = new LostMessage();
            try {
                lm.f();
            } finally {
                lm.dispose();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
} /* Output:
A trivial exception
*///:~
