package containers;//: containers/RandomBounds.java
// Does Math.random() produce 0.0 and 1.0?
// {RunByHand}

import static net.mindview.util.Print.print;


/**
 * 507 页
 */

public class RandomBounds {
    static void usage() {
        print("Usage:");
        print("\tRandomBounds lower");
        print("\tRandomBounds upper");
        System.exit(1);
    }

    public static void main(String[] args) {

        String[] args1 = new String []{"lower"};

        if (args1[0].equals("lower")) {
            while (Math.random() != 0.0)
                ; // Keep trying
            print("Produced 0.0!");
        } else if (args1[0].equals("upper")) {
            while (Math.random() != 1.0)
                ; // Keep trying
            print("Produced 1.0!");
        } else
            usage();
    }
} ///:~
