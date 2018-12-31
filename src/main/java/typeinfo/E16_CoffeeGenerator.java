package typeinfo;

import net.mindview.util.Generator;

import java.util.Iterator;


/*****
 *
 *
 *
 *
 *
 * 333页
 *
 *
 * 修改第15章中的Coffee继承结构，以便可以使用注册工厂
 *
 *
 * 1
 *
 */
public class E16_CoffeeGenerator
        implements Generator<Coffee>, Iterable<Coffee> {
    public E16_CoffeeGenerator() {
    }

    private int size = 0;

    public E16_CoffeeGenerator(int sz) {
        size = sz;
    }

    public Coffee next() {
        return Coffee.createRandom();
    }

    class CoffeeIterator implements Iterator<Coffee> {
        int count = size;

        public boolean hasNext() {
            return count > 0;
        }

        public Coffee next() {
            count--;
            //return E16_CoffeeGenerator.this.next();
            return E16_CoffeeGenerator.this.next();
        }

        public void remove() {
        } // Not implemented
    }

    ;

    public Iterator<Coffee> iterator() {
        return new CoffeeIterator();
    }

    public static void main(String[] args) {
        for (Coffee c : new E16_CoffeeGenerator(10))
            System.out.println(c);
    }
} 