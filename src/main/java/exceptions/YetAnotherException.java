//: exceptions/E18_LostMessage.java
/****************** Exercise 18 *****************
 * Add a second level of exception loss to
 * LostMessage.java so that the HoHumException is
 * itself replaced by a third exception.
 ***********************************************/
package exceptions;

class YetAnotherException extends Exception {
    public String toString() {
        return "Yet another exception";
    }
}

class LostMessage2 {
    void f() throws VeryImportantException {
        throw new VeryImportantException();
    }

    void dispose() throws HoHumException {
        throw new HoHumException();
    }

    void cleanup() throws YetAnotherException {
        throw new YetAnotherException();
    }
}
                                       