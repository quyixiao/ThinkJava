package io;//: io/TransferTo.java
// Using transferTo() between channels
// {Args: TransferTo.java TransferTo.txt}

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;


/**
 *
 *
 * 554 页
 *
 *
 *
 */
public class TransferTo {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("arguments: sourcefile destfile");
            System.exit(1);
        }
        FileChannel
                in = new FileInputStream(args[0]).getChannel(),
                out = new FileOutputStream(args[1]).getChannel();
        in.transferTo(0, in.size(), out);
        // Or:
        // out.transferFrom(in, 0, in.size());
    }
} ///:~
