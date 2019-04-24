package java8.stream;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;


/****
 * Collection提供了新的Stream()方法
 * 流不存储值，通过管道的方法获取值
 * 本质是函数式，对流的操作会生成一个结果，不过，并不会修改底层的数据源，集合可能作为流的底层数据源
 * 延迟查找，很多流操作（过滤，映射，排序等）都可以延迟实现
 *
 */
public class StreamTest3_Reduce {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //集合中所有的元素*2 再累加
        System.out.println(list.stream().map(i -> i * 2).reduce(0, Integer::sum));


        Borrow borrow1 = new Borrow(new BigDecimal(1),new BigDecimal(2));
        Borrow borrow2 = new Borrow(new BigDecimal(3),new BigDecimal(4));
        List<Borrow> list1 = Arrays.asList(borrow1,borrow2);
        //集合中所有的元素*2 再累加
        BigDecimal c = list1.stream().map(i ->
            BigDecimalUtil.add(i.getA(),i.getB())
        ).reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println(c);


    }
}
