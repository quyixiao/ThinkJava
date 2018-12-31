package containers;//: containers/Groundhog2.java
// A class that's used as a key in a HashMap
// must override hashCode() and equals().


/**
 *  Groundhog2.hashCode 返回Groundhog 的标识数字，作为散列码，在此例中，程序
 *  员负责确保不同的Groundhog具有不同的编号，hashCode()并不需要总是能够返回唯一
 *  的标识码，稍后读者将会理解其原因，但是equals() 方法必须严格的判断两个对象是不是
 *  相同，此处的equeal()是判断Groundhog的号码，所以作为Hashmap中的键，如果两个
 *  Grouphog2对象具有相同的Grouphog编号，程序就出错了
 *
 *
 *
 *
 *  尽管看起来equals() 方法只是检查其参数是否是Groundhog2的实例，使用第14章中介绍的
 *  instanceof 关键字，但是instanceof悄悄的检查了此对象是否为null ，因为如果instanceof
 *  左边的的参数为null,它会返回false, 如果equals 的参数不为null且类型不正确，则基于每个对象
 *  中实际的number 数值进行比较，从输出中可以看到，现在的方式是正确的
 *
 *
 *
 *
 */
public class Groundhog2 extends Groundhog {



    public Groundhog2(int n) {
        super(n);
    }




    public int hashCode() {
        return number;
    }




    public boolean equals(Object o) {
        return o instanceof Groundhog2 &&
                (number == ((Groundhog2) o).number);
    }



} ///:~
