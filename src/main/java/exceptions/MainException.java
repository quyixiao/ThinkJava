package exceptions;//: exceptions/MainException.java

import java.io.FileInputStream;


/****
 * 279页
 *
 *
 * 1
 */
public class MainException {
    // Pass all exceptions to the console:
    public static void main(String[] args) throws Exception {
        // Open the file:
        FileInputStream file =
                new FileInputStream("MainException.java");
        // Use the file ...
        // Close the file:
        file.close();
    }
} ///:~
