package generics;//: generics/NeedCasting.java

import java.io.FileInputStream;
import java.io.ObjectInputStream;


/****
 * 403页
 *
 *
 * 1
 */
public class NeedCasting {
    @SuppressWarnings("unchecked")
    public void f(String[] args) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(args[0]));
        //List<Widget> shapes = (List<Widget>)in.readObject();
    }
} ///:~
