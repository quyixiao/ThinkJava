package containers;//: containers/TypesForSets.java
// Methods necessary to put your own type in a Set.

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * 477
 * <p>
 * <p>
 * 存入Set的每个元素都必须是唯一的，因为Set不保存重复的元素，加入Set的每一个元素必须定义eques方法以确保对象的唯一性
 * ，Set与Collection有完全一样的接口，Set接口不保证维护元素的次序
 * 为了快速查找而设计的Set，存入HashSet的元素必须定义hashCode()
 * 保持次序的Set,底层为树结构，使用它可以从Set中提取有序的序列，元素必须实现Compareble接口
 * LinkedHashSet 具有HashSet的查询速度，且内部使用链表护元素顺序（插入的次序），于是在使用
 * 迭代器遍历Set时，结果会按元素插入的次序显示，元素必须定义HashCode()方法
 * <p>
 * <p>
 * 但是对于良好的编程风格而言，你应该在覆盖equeals()方法时，总是同时覆盖hashCode()方法
 * <p>
 * 不懂
 * <p>
 * <p>
 * <p>
 * 如果我们尝试着将没有恰当的支持必需的操作的类型用于需要这些方法的Set,那么就会有大麻烦了，
 * 对于没有重新定义hashCode()方法的SetType或TreeType，如果将它们放置到任何散列实现中都
 * 会产生重复值，这样就违反了Set的基本契约，这相当的烦人，因为这甚至不会有运行时错误，但是
 * ，默认的hashCode()是合法的，因此这是合法的行为，即便它不正确，确保这种程序的正确性的唯一
 * 靠方法就是将单元测试合并到你的构建的系统中
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 1
 */
class SetType {
    int i;

    public SetType(int n) {
        i = n;
    }

    public boolean equals(Object o) {
        boolean result = o instanceof SetType && (i == ((SetType) o).i);
        //LoggerUtils.info(result+"");
        return result;
    }


    public String toString() {
        return Integer.toString(i);
    }
}

class HashType extends SetType {
    public HashType(int n) {
        super(n);
    }

    public int hashCode() {
        return i;
    }
}

class TreeType extends SetType
        implements Comparable<TreeType> {
    public TreeType(int n) {
        super(n);
    }

    public int compareTo(TreeType arg) {
        int result = (arg.i < i ? -1 : (arg.i == i ? 0 : 1));

        // LoggerUtils.info(result  );
        return result;
    }
}

public class TypesForSets {
    static <T> Set<T> fill(Set<T> set, Class<T> type) {
        try {
            for (int i = 0; i < 10; i++) {
                set.add(type.getConstructor(int.class).newInstance(i));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return set;
    }

    static <T> void test(Set<T> set, Class<T> type) {
        fill(set, type);
        System.out.println(set);
        fill(set, type); // Try to add duplicates
        System.out.println(set);
        fill(set, type);
        System.out.println(set);
    }

    public static void main(String[] args) {
        test(new HashSet<HashType>(), HashType.class);
        System.out.println("=============11111111111===============================");
        test(new LinkedHashSet<HashType>(), HashType.class);
        System.out.println("==============22222222222==============================");
        test(new TreeSet<TreeType>(), TreeType.class);
        System.out.println("==============33333333333333=======+++++++++++++++++++++++");
        // Things that don't work:
        test(new HashSet<SetType>(), SetType.class);
        System.out.println("-------------444444444444444----------------------------");
        test(new HashSet<TreeType>(), TreeType.class);
        System.out.println("-----------------------3----------------333");
        test(new LinkedHashSet<SetType>(), SetType.class);
        test(new LinkedHashSet<TreeType>(), TreeType.class);


        System.out.println("111111111111111111111111111");
        try {
            test(new TreeSet<SetType>(), SetType.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            test(new TreeSet<HashType>(), HashType.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}




/* Output: (Sample)
[2, 4, 9, 8, 6, 1, 3, 7, 5, 0]
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
[9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
[9, 9, 7, 5, 1, 2, 6, 3, 0, 7, 2, 4, 4, 7, 9, 1, 3, 6, 2, 4, 3, 0, 5, 0, 8, 8, 8, 6, 5, 1]
[0, 5, 5, 6, 5, 0, 3, 1, 9, 8, 4, 2, 3, 9, 7, 3, 4, 4, 0, 7, 1, 9, 6, 2, 1, 8, 2, 8, 6, 7]
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
java.lang.ClassCastException: SetType cannot be cast to java.lang.Comparable
java.lang.ClassCastException: HashType cannot be cast to java.lang.Comparable
*///:~
