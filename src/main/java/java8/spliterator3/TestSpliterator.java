package java8.spliterator3;


import java.util.ArrayList;
import java.util.Spliterator;
import java.util.function.Consumer;

public class TestSpliterator {
    public static void main(String[] args) {
        test_forEachRemaining();
        System.out.println("---------------------");
        test_tryAdvance();
    }

    public static void test_tryAdvance() {
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i <= 10; i++) al.add(i);
        ArrayListSpliterator1 als_1 = new ArrayListSpliterator1(al, 0, -1);
        als_1.tryAdvance(new Consumer<Integer>() {
            @Override
            public void accept(Integer t) {
                System.out.print(t + " ");
            }
        });
        System.out.println("\nals_1:" + als_1);
        System.out.println("left size:" + als_1.estimateSize());
    }

    public static void test_forEachRemaining() {
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            al.add(i);
        }
        ArrayListSpliterator1 als_1 = new ArrayListSpliterator1(al, 0, -1);
        als_1.forEachRemaining(new Consumer<Integer>() {
            @Override
            public void accept(Integer t) {
                System.out.print(t + " ");
            }
        });
        System.out.println("\nals_1:" + als_1);
        System.out.println("left size:" + als_1.estimateSize());
    }


    static final class ArrayListSpliterator1<E> implements Spliterator<E> {

        //用于存放实体变量的list
        private final ArrayList<E> list;
        //遍历的当前位置
        private int index;
        //结束位置(不包括) 意思是当前可用的元素是[index, fence) = [index, fence-1]
        private int fence; // -1 until used; then one past last index

        // 构造方法
        ArrayListSpliterator1(ArrayList<E> list, int origin, int fence) {
            this.list = list;
            this.index = origin;
            this.fence = fence;
        }

        //第一次使用的时候初始化fence 返回结束位置
        private int getFence() { // initialize fence to size on first use
            int hi; // (a specialized variant appears in method forEach)
            ArrayList<E> lst;
            if ((hi = fence) < 0) {
                if ((lst = list) == null)
                    hi = fence = 0;
                else {
                    hi = fence = lst.size();
                }
            }
            return hi;
        }

        /**
         * 根据当前的Spliterator拆分出一个新的Spliterator
         * 相当于二分,
         * Note:共享同一个list,改变的只是下标
         */
        public ArrayListSpliterator1<E> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid) ? null : // divide range in half unless too small
                    new ArrayListSpliterator1<E>(list, lo, index = mid);
        }

        //单次遍历  下标index只加1
        public boolean tryAdvance(Consumer<? super E> action) {
            if (action == null)
                throw new NullPointerException();
            int hi = getFence(), i = index;
            if (i < hi) {
                index = i + 1;
                @SuppressWarnings("unchecked") E e = (E) list.get(i);
                action.accept(e);
                return true;
            }
            return false;
        }

        //整体遍历
        public void forEachRemaining(Consumer<? super E> action) {
            int i, hi, mc; // hoist accesses and checks from loop
            ArrayList<E> lst;
            Object[] a;
            if (action == null)
                throw new NullPointerException();
            if ((lst = list) != null && (a = lst.toArray()) != null) {
                if ((hi = fence) < 0) {
                    hi = lst.size();
                }
                if ((i = index) >= 0 && (index = hi) <= a.length) {
                    for (; i < hi; ++i) {
                        @SuppressWarnings("unchecked") E e = (E) a[i];
                        action.accept(e);
                    }
                }
            }
        }

        //剩下还有多少元素
        public long estimateSize() {
            return (long) (getFence() - index);
        }

        public int characteristics() {
            return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
        }

        public String toString() {
            return "[" + this.index + "," + getFence() + "]";
        }
    }


}


