package generics;//: generics/RestrictedComparablePets.java


/***
 *      Hamster说明再次实现ComparablePet中的相同的接口是可能的，只要它们精确的相同，包括
 *  参数类型在内，但是，这只是与覆盖基类中的方法相同，就像在Gecko中看到的那样
 *
 *
 *  1
 */
class Hamster extends ComparablePet implements Comparable<ComparablePet> {
    public int compareTo(ComparablePet arg) {
        return 0;
    }
}

// Or just:

class Gecko extends ComparablePet {
    public int compareTo(ComparablePet arg) {
        return 0;
    }
} ///:~
