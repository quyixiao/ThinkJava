package java8.spliterator2;

import java.util.ArrayList;
import java.util.Spliterator;


public class SpliteratorInArrayListStudy {
    public static void main(String[] args) {
        // 初始化list
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 20; i++) {
            list.add(i + 1);
        }
        //四线程均分配比方式
        Spliterator<Integer> spliterator01 = list.spliterator();            //01中有20个元素
        Spliterator<Integer> spliterator02 = spliterator01.trySplit();    //01中有10个元素,02中有10个元素
        Spliterator<Integer> spliterator03 = spliterator01.trySplit();    //01中有5个元素,02中有10个元素,03中有5个元素
      //  Spliterator<Integer> spliterator04 = spliterator02.trySplit();    //01中有5个元素,02中有5个元素,03中有5个元素,04中有5个元素
        MyThread4Spliterator<Integer> t01 = new MyThread4Spliterator<Integer>(spliterator01);
        MyThread4Spliterator<Integer> t02 = new MyThread4Spliterator<Integer>(spliterator02);
        MyThread4Spliterator<Integer> t03 = new MyThread4Spliterator<Integer>(spliterator03);
     //   MyThread4Spliterator<Integer> t04 = new MyThread4Spliterator<Integer>(spliterator04);
        t01.setName("001");
        t02.setName("002");
        t03.setName("003");
        //t04.setName("004");

        t01.start();
        t02.start();
        t03.start();
        //t04.start();
    }
}