package concurrency.jvm;

public class MyTest4 {


    //正如前面各节所示，在java中，你可以选择如何实现并发编程，并且这个选择会令人困惑
    // 这个问题通常来牌用来描述并必程序技术的术语，特别是涉及线程的那些
    // 到目前为止，你应该看到要执行的驱动它线程之间有一个差异，这个差异在java类库中尤为明显，
    //因为你对thread类实际没有任何控制权，并且这种隔离在使用执行器时更加
    //明显，因为执行器将替你处理线程在创建和管理，你创建任务，并通过某种方式将一个线程附着到
    //任务上，以使得这个线程可驱动任务，所以我认为这Task应该是好得字，如果接口只是其方法的返回型封装
    //

    //对于数组来说，JavaDoc经常将构成数组的元素为Component,实际上就是将数据降低一个维度后的类型




    public static void main(String[] args) {
       /* MyParent4 myParent4 = new MyParent4();
        System.out.println("=============");

        MyParent4 myParent5 = new MyParent4();
*/

       MyParent4 [] myParent4s = new MyParent4[1];








    }
}


class MyParent4{
    static {
        System.out.println("MYparent4 static block");
    }
}