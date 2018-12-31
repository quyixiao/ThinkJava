package interfaces;//: generics/Fill.java
// Generalizing the FilledList idea
// {main: FillTest}

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// Doesn't work with "anything that has an add()." There is
// no "Addable" interface so we are narrowed to using a
// Collection. We cannot generalize using generics in
// this case.
public class Fill {
    public static <T> void fill(Collection<T> collection,
                                Class<? extends T> classToken, int size) {
        for (int i = 0; i < size; i++)
            // Assumes default constructor:
            try {
                collection.add(classToken.newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }
}

class Contract {
    private static long counter = 0;
    private final long id = counter++;

    public String toString() {
        return getClass().getName() + " " + id;
    }
}

class TitleTransfer extends Contract {
}


/**
 * 表示有一点进退两难的情况，为了使某种可以试验一下，将这些边界，将这些边界和通配符
 * 拿出来，你就会发现某些Apply和FilledList应用将无法工作，表示有点进退两难的情况，
 * 为了使某 种类型可用，它必须默认，但是没有任何方式可以在
 * 唉，大多数类都是在不了解
 */
class FillTest {
    public static void main(String[] args) {
        List<Contract> contracts = new ArrayList<Contract>();
        Fill.fill(contracts, Contract.class, 3);
        Fill.fill(contracts, TitleTransfer.class, 2);
        for (Contract c : contracts) {
            System.out.println(c);
        }
        SimpleQueue<Contract> contractQueue = new SimpleQueue<Contract>();
        // Won't work. fill() is not generic enough:
        // Fill.fill(contractQueue, Contract.class, 3);
    }
} /* Output:
Contract 0
Contract 1
Contract 2
TitleTransfer 3
TitleTransfer 4
*///:~
